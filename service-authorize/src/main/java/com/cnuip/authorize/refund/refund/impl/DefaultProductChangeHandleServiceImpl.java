package com.cnuip.authorize.refund.refund.impl;

import com.ihomefnt.aladdin.common.dto.log.OperateLogDto;
import com.ihomefnt.aladdin.common.dto.log.OrderId;
import com.ihomefnt.aladdin.common.enums.OperateTemplate;
import com.ihomefnt.aladdin.common.service.OperateLogService;
import com.ihomefnt.aladdin.dms.enums.ProductChangeType;
import com.ihomefnt.aladdin.dms.refund.dto.ProductChangeContext;
import com.ihomefnt.aladdin.dms.refund.processor.impl.SaveDestroyProductProcessor;
import com.ihomefnt.aladdin.dms.refund.processor.impl.SaveNeedLogisticProductProcessor;
import com.ihomefnt.aladdin.dms.refund.processor.impl.SaveSellOffProductProcessor;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import com.ihomefnt.aladdin.dms.refund.service.ProductChangeHandleService;
import com.ihomefnt.aladdin.dms.service.ProductChangeTaskService;
import com.ihomefnt.aladdin.dms.vo.purchase.request.ChangeProductVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("productChangeHandleService")
public class DefaultProductChangeHandleServiceImpl implements ProductChangeHandleService {
    @Resource
    private Processor saveHandleMethodProcessor;

    @Resource
    private Processor saveLogisticInfoProcessor;

    private Map<Integer, ProductChangeHandleService> handleServiceMap;

    @Resource
    private ProductChangeHandleService manufacturerNoShipPchImpl;

    @Resource
    private ProductChangeHandleService returnToManufacturerPchImpl;

    @Resource
    private ProductChangeHandleService returnToWarehousePchImpl;

    @Resource
    private ProductChangeHandleService returnToLogisticsPchImpl;

    @Resource
    private ProductChangeHandleService returnToDecorationCompanyPchImpl;

    @Resource
    private ProductChangeHandleService destoryPchImpl;

    @Resource
    private ProductChangeHandleService sellOffPchImpl;

    @Resource
    OperateLogService operateLogService;

    @Resource
    ProductChangeTaskService productChangeTaskService;

    @Resource
    private SaveDestroyProductProcessor saveDestroyProductProcessor;

    @Resource
    private SaveSellOffProductProcessor saveSellOffProductProcessor;

    @Resource
    private SaveNeedLogisticProductProcessor saveNeedLogisticProductProcessor;

    @PostConstruct
    public void init() {
        handleServiceMap = new HashMap<>();
        handleServiceMap.put(0,manufacturerNoShipPchImpl);
        handleServiceMap.put(1,returnToWarehousePchImpl);
        handleServiceMap.put(2,returnToWarehousePchImpl);
        handleServiceMap.put(3,returnToManufacturerPchImpl);
        handleServiceMap.put(4,destoryPchImpl);
        handleServiceMap.put(5,sellOffPchImpl);
        handleServiceMap.put(6,returnToLogisticsPchImpl);
        handleServiceMap.put(7,returnToManufacturerPchImpl);
        handleServiceMap.put(8,returnToDecorationCompanyPchImpl);
    }

    @Override
    public void changePurchaseInfo(ProductChangeContext context) {
        List<ChangeProductVo> productChangeDetails = context.getChangeProductVos();
        if (!CollectionUtils.isEmpty(productChangeDetails)) {
            context.setNeedLogisiticsProcess(new ArrayList<>());
            context.setNeedDestory(new ArrayList<>());
            context.setNeedSellOff(new ArrayList<>());
            Map<Integer, List<ChangeProductVo>> productChangeDetailMap = productChangeDetails.stream().collect(Collectors.groupingBy(ChangeProductVo::getHandleMethod));
            for (Map.Entry<Integer, List<ChangeProductVo>> entry : productChangeDetailMap.entrySet()) {
                ProductChangeHandleService productChangeHandleService = handleServiceMap.get(entry.getKey());
                ProductChangeContext productChangeContext = new ProductChangeContext();
                productChangeContext.setChangeProductVos(entry.getValue());
                productChangeHandleService.changePurchaseInfo(productChangeContext);
                context.getNeedLogisiticsProcess().addAll(productChangeContext.getNeedLogisiticsProcess());
                context.getNeedDestory().addAll(productChangeContext.getNeedDestory());
                context.getNeedSellOff().addAll(productChangeContext.getNeedSellOff());
            }

            if (!CollectionUtils.isEmpty(context.getNeedLogisiticsProcess())) {
                saveNeedLogisticProductProcessor.process(context);
            }

            if (!CollectionUtils.isEmpty(context.getNeedDestory())) {
                saveDestroyProductProcessor.process(context);
            }

            if (!CollectionUtils.isEmpty(context.getNeedSellOff())) {
                saveSellOffProductProcessor.process(context);
            }
            saveHandleMethodProcessor.process(context);
        }

        OperateLogDto operateLogDto = new OperateLogDto(new OrderId(context.getProductChangeRecord().getOrderId()),context.getOperatorId(), context.getProductChangeRecord().getId(), OperateTemplate.PRODUCT_APPROVAL_PURCHASE_DEAL);
        operateLogDto.setChangeTypeName(ProductChangeType.getNameByValue(context.getProductChangeRecord().getChangeType()));
        operateLogService.recordLog(operateLogDto);
    }

    @Override
    public void changeLogisticInfo(ProductChangeContext context) {
        List<ChangeProductVo> productChangeDetails = context.getChangeProductVos();
        if (!CollectionUtils.isEmpty(productChangeDetails)) {
            Map<Integer, List<ChangeProductVo>> productChangeDetailMap = productChangeDetails.stream().collect(Collectors.groupingBy(ChangeProductVo::getHandleMethod));
            for (Map.Entry<Integer, List<ChangeProductVo>> entry : productChangeDetailMap.entrySet()) {
                ProductChangeHandleService productChangeHandleService = handleServiceMap.get(entry.getKey());
                ProductChangeContext productChangeContext = new ProductChangeContext();
                productChangeContext.setChangeProductVos(entry.getValue());
                productChangeHandleService.changeLogisticInfo(productChangeContext);
            }
            saveLogisticInfoProcessor.process(context);
        }

        // log
        OperateLogDto operateLogDto = new OperateLogDto(new OrderId(context.getProductChangeLogisticRecord().getOrderId()),context.getOperatorId(), context.getProductChangeLogisticRecord().getId(), OperateTemplate.PRODUCT_APPROVAL_LOGISTICS_DEAL);
        operateLogDto.setChangeTypeName(ProductChangeType.getNameByValue(context.getProductChangeLogisticRecord().getChangeType()));
        operateLogService.recordLog(operateLogDto);


        // 完成物流任务
        productChangeTaskService.newlaunchProductChangeLogisticsTask(context.getProductChangeLogisticRecord());
    }

    @Override
    public void finishServiceOrder(ProductChangeContext context) {
        List<ChangeProductVo> productChangeDetails = context.getChangeProductVos();
        if (!CollectionUtils.isEmpty(productChangeDetails)) {
            Map<Integer, List<ChangeProductVo>> productChangeDetailMap = productChangeDetails.stream().collect(Collectors.groupingBy(ChangeProductVo::getHandleMethod));
            for (Map.Entry<Integer, List<ChangeProductVo>> entry : productChangeDetailMap.entrySet()) {
                ProductChangeHandleService productChangeHandleService = handleServiceMap.get(entry.getKey());
                ProductChangeContext productChangeContext = new ProductChangeContext();
                productChangeContext.setChangeProductVos(entry.getValue());
                productChangeHandleService.finishServiceOrder(productChangeContext);
            }
        }
    }

    @Override
    public void approvalPass(ProductChangeContext context) {
        List<ChangeProductVo> productChangeDetails = context.getChangeProductVos();
        if (!CollectionUtils.isEmpty(productChangeDetails)) {
            Map<Integer, List<ChangeProductVo>> productChangeDetailMap = productChangeDetails.stream().collect(Collectors.groupingBy(ChangeProductVo::getHandleMethod));
            for (Map.Entry<Integer, List<ChangeProductVo>> entry : productChangeDetailMap.entrySet()) {
                ProductChangeHandleService productChangeHandleService = handleServiceMap.get(entry.getKey());
                ProductChangeContext productChangeContext = new ProductChangeContext();
                productChangeContext.setChangeProductVos(entry.getValue());
                productChangeHandleService.approvalPass(productChangeContext);
            }
        }
    }
}
