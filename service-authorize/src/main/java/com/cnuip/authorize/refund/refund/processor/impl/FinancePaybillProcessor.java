package com.cnuip.authorize.refund.refund.processor.impl;

import com.ihomefnt.aladdin.dms.domain.NPurchaseOrderDetailMoreInfo;
import com.ihomefnt.aladdin.dms.dto.financial.FinanceSoftBillCreateDto;
import com.ihomefnt.aladdin.dms.enums.BetaQueryPurchaseType;
import com.ihomefnt.aladdin.dms.enums.finance.FinancePayableBillTypeEnum;
import com.ihomefnt.aladdin.dms.refund.dto.ProductChangeContext;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import com.ihomefnt.aladdin.dms.repository.NPurchaseOrderDetailInfoRepository;
import com.ihomefnt.aladdin.dms.repository.NPurchaseOrderRepository;
import com.ihomefnt.aladdin.dms.service.impl.finance.FinancePayableBillFactory;
import com.ihomefnt.aladdin.dms.vo.purchase.request.ChangeProductVo;
import com.ihomefnt.aladdin.psi.controller.purchase.po.NPurchaseOrder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 应付单处理
 */
@Service
public class FinancePaybillProcessor implements Processor {
    @Resource
    private FinancePayableBillFactory financePayableBillFactory;

    @Resource
    private NPurchaseOrderDetailInfoRepository nPurchaseOrderDetailInfoRepository;

    @Resource
    private NPurchaseOrderRepository nPurchaseOrderRepository;


    @Override
    public void process(ProductChangeContext context) {
        if (CollectionUtils.isEmpty(context.getChangeProductVos()) || context.getProductChangeRecord() == null) {
            return;
        }

        Map<Integer, List<ChangeProductVo>> changeProductVoMap = context.getChangeProductVos().stream().collect(Collectors.groupingBy(ChangeProductVo::getHandleMethod));

        for (Map.Entry<Integer, List<ChangeProductVo>> entry : changeProductVoMap.entrySet()) {
            Integer handleMethod = entry.getKey();
            FinancePayableBillTypeEnum billTypeEnum = null;
            if (context.getChangeType() == 1) {
                // 软装采购订单应付单
                switch (handleMethod){
                    case 0 :
                        billTypeEnum = FinancePayableBillTypeEnum.PRODUCT_CHANGE_RETURN_ONE;
                        break;
                    case 1:
                        billTypeEnum = FinancePayableBillTypeEnum.PRODUCT_CHANGE_RETURN_TWO;
                        break;
                    case 2:
                        billTypeEnum = FinancePayableBillTypeEnum.PRODUCT_CHANGE_RETURN_THREE;
                        break;
                    case 3:
                        billTypeEnum = FinancePayableBillTypeEnum.PRODUCT_CHANGE_RETURN_FOUR;
                        break;
                    case 7:
                        billTypeEnum = FinancePayableBillTypeEnum.PRODUCT_CHANGE_RETURN_FIVE;
                        break;
                    default:
                        break;
                }
            } else if (context.getChangeType() == 11) {
                // 硬装采购订单应付单
                switch (handleMethod){
                    case 0 :
                        billTypeEnum = FinancePayableBillTypeEnum.HARD_CHANGE_RETURN_ONE;
                        break;
                    case 1:
                        billTypeEnum = FinancePayableBillTypeEnum.HARD_CHANGE_RETURN_TWO;
                        break;
                    case 2:
                        billTypeEnum = FinancePayableBillTypeEnum.HARD_CHANGE_RETURN_THREE;
                        break;
                    case 3:
                        billTypeEnum = FinancePayableBillTypeEnum.HARD_CHANGE_RETURN_FOUR;
                        break;
                    default:
                        break;
                }
            }

            List<String> deliverKeys = new ArrayList<>();
            for (ChangeProductVo changeProductVo : entry.getValue()) {
                if (!CollectionUtils.isEmpty(changeProductVo.getDeliverKeys())) {
                    deliverKeys.addAll(changeProductVo.getDeliverKeys());
                }
            }

            List<NPurchaseOrderDetailMoreInfo> detailMoreInfos = nPurchaseOrderDetailInfoRepository.batchQueryMoreInfoByDeliverKey(deliverKeys);

            if (!CollectionUtils.isEmpty(detailMoreInfos)) {
                Map<Long, List<NPurchaseOrderDetailMoreInfo>> map = detailMoreInfos.stream().collect(Collectors.groupingBy(NPurchaseOrderDetailMoreInfo::getPurchaseOrderId));
                for (Map.Entry<Long, List<NPurchaseOrderDetailMoreInfo>> moreInfoEntry : map.entrySet()) {
                    Long purchaseOrderId = moreInfoEntry.getKey();
                    NPurchaseOrder purchaseOrder = nPurchaseOrderRepository.queryPurchaseOrderById(purchaseOrderId);
                    if(null == purchaseOrder){
                        continue;
                    }

                    // 硬装非艾佳自采不推送应付单
                    if (purchaseOrder.getPurchaseOrderType() == 1 && !purchaseOrder.getPurchaseType().equals(BetaQueryPurchaseType.AIJIA_SEIL_PURCHASE.getCode())){
                        continue;
                    }
                    FinanceSoftBillCreateDto createDto = new FinanceSoftBillCreateDto();
                    createDto.setTypeEnum(billTypeEnum);
                    createDto.setOperatorId(context.getOperatorId());
                    createDto.setBusinessNo(purchaseOrder.getOrderNum());
                    createDto.setChangeDetails(moreInfoEntry.getValue());
                    createDto.setAfterSaleType(context.getAfterSaleType());
                    createDto.setOrderNo(context.getOrderId().intValue());
                    createDto.setPurchaseOrderStatus(purchaseOrder.getOrderStatus());
                    financePayableBillFactory.getFinanceService(billTypeEnum).createSoftPayableBill(createDto);
                }

            }
        }

    }
}
