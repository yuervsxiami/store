package com.cnuip.authorize.refund.refund.impl;

import com.ihomefnt.aladdin.dms.refund.dto.ProductChangeContext;
import com.ihomefnt.aladdin.dms.refund.processor.impl.*;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import com.ihomefnt.aladdin.dms.refund.service.ProductChangeHandleService;
import com.ihomefnt.aladdin.dms.vo.purchase.request.ChangeProductVo;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractProductChangeHandleServiceImpl implements ProductChangeHandleService {
    @Resource
    protected DeliverProductProcessor deliverProductProcessor;

    @Resource
    protected FinancePaybillProcessor financePaybillProcessor;

    @Resource
    protected FinanceWarehouseProcessor financeWarehouseProcessor;

    @Resource
    protected InstallCheckOrderProcessor installCheckOrderProcessor;

    @Resource
    protected InstallOrderProcessor installOrderProcessor;

    @Resource
    protected LogisticsPlanProcessor logisticsPlanProcessor;

    @Resource
    protected LogisticsProcessor logisticsProcessor;

    @Resource
    protected PurchaseProcessor purchaseProcessor;

    @Resource
    protected SaveLogisticInfoProcessor saveLogisticInfoProcessor;

    @Resource
    protected ServiceOrderProcessor serviceOrderProcessor;

    @Resource
    protected WarehouseItemProcessor warehouseItemProcessor;

    @Resource
    protected AllotProcessor allotProcessor;

    @Resource
    protected SaveHandleMethodProcessor saveHandleMethodProcessor;

    public List<Processor> getChangePurchaseProcessors(int status) {
        return null;
    }

    public List<Processor> getChangeLogisticProcessors(int status) {
        return null;
    }

    public List<Processor> getFinishServiceOrderProcessors(int status) {
        return null;
    }

    public List<Processor> getApprovalPass(int status) {
        return null;
    }

    @Override
    public void changePurchaseInfo(ProductChangeContext context) {
        List<ChangeProductVo> productVos = context.getChangeProductVos();
        if (CollectionUtils.isEmpty(productVos)) {
            return;
        }

        if (CollectionUtils.isEmpty(context.getNeedLogisiticsProcess())) {
            context.setNeedLogisiticsProcess(new ArrayList<>());
        }
        if (CollectionUtils.isEmpty(context.getNeedSellOff())) {
            context.setNeedSellOff(new ArrayList<>());
        }
        if (CollectionUtils.isEmpty(context.getNeedDestory())) {
            context.setNeedDestory(new ArrayList<>());
        }

        Map<Integer, List<ChangeProductVo>> productMap = productVos.stream().collect(Collectors.groupingBy(ChangeProductVo::getProductStatus));
        for (Map.Entry<Integer, List<ChangeProductVo>> entry : productMap.entrySet()) {
            List<Processor> processors = getChangePurchaseProcessors(entry.getKey());
            if (!CollectionUtils.isEmpty(processors)) {
                ProductChangeContext subContext = new ProductChangeContext();
                subContext.setChangeProductVos(entry.getValue());
                for (Processor processor : processors) {
                    processor.process(subContext);
                }
                if (!CollectionUtils.isEmpty(subContext.getNeedLogisiticsProcess())) {
                    context.getNeedLogisiticsProcess().addAll(subContext.getNeedLogisiticsProcess());
                }
                if (!CollectionUtils.isEmpty(subContext.getNeedSellOff())) {
                    context.getNeedSellOff().addAll(subContext.getNeedSellOff());
                }
                if (!CollectionUtils.isEmpty(subContext.getNeedDestory())) {
                    context.getNeedDestory().addAll(subContext.getNeedDestory());
                }
            }
        }

    }

    @Override
    public void changeLogisticInfo(ProductChangeContext context) {
        List<ChangeProductVo> productVos = context.getChangeProductVos();
        if (CollectionUtils.isEmpty(productVos)) {
            return;
        }

        Map<Integer, List<ChangeProductVo>> productMap = productVos.stream().collect(Collectors.groupingBy(ChangeProductVo::getProductStatus));
        for (Map.Entry<Integer, List<ChangeProductVo>> entry : productMap.entrySet()) {
            List<Processor> processors = getChangeLogisticProcessors(entry.getKey());
            if (!CollectionUtils.isEmpty(processors)) {
                ProductChangeContext subContext = new ProductChangeContext();
                subContext.setChangeProductVos(entry.getValue());
                for (Processor processor : processors) {
                    processor.process(subContext);
                }
            }
        }
    }

    @Override
    public void finishServiceOrder(ProductChangeContext context) {
        List<ChangeProductVo> productVos = context.getChangeProductVos();
        if (CollectionUtils.isEmpty(productVos)) {
            return;
        }

        Map<Integer, List<ChangeProductVo>> productMap = productVos.stream().collect(Collectors.groupingBy(ChangeProductVo::getProductStatus));
        for (Map.Entry<Integer, List<ChangeProductVo>> entry : productMap.entrySet()) {
            List<Processor> processors = getFinishServiceOrderProcessors(entry.getKey());
            if (!CollectionUtils.isEmpty(processors)) {
                ProductChangeContext subContext = new ProductChangeContext();
                subContext.setChangeProductVos(entry.getValue());
                for (Processor processor : processors) {
                    processor.process(subContext);
                }
            }
        }
    }

    @Override
    public void approvalPass(ProductChangeContext context) {
        List<ChangeProductVo> productVos = context.getChangeProductVos();
        if (CollectionUtils.isEmpty(productVos)) {
            return;
        }

        Map<Integer, List<ChangeProductVo>> productMap = productVos.stream().collect(Collectors.groupingBy(ChangeProductVo::getProductStatus));
        for (Map.Entry<Integer, List<ChangeProductVo>> entry : productMap.entrySet()) {
            List<Processor> processors = getApprovalPass(entry.getKey());
            if (!CollectionUtils.isEmpty(processors)) {
                ProductChangeContext subContext = new ProductChangeContext();
                subContext.setChangeProductVos(entry.getValue());
                for (Processor processor : processors) {
                    processor.process(subContext);
                }
            }
        }
    }
}
