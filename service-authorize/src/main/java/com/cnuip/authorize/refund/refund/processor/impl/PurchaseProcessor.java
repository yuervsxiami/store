package com.cnuip.authorize.refund.refund.processor.impl;

import com.ihomefnt.aladdin.dms.domain.DeliverProductStatus;
import com.ihomefnt.aladdin.dms.domain.NPurchaseOrderDetailInfo;
import com.ihomefnt.aladdin.dms.refund.dto.ProductChangeContext;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import com.ihomefnt.aladdin.dms.repository.DeliverProductStatusRepository;
import com.ihomefnt.aladdin.dms.repository.NPurchaseOrderDetailInfoRepository;
import com.ihomefnt.aladdin.dms.repository.NPurchaseOrderRepository;
import com.ihomefnt.aladdin.dms.vo.purchase.request.ChangeProductVo;
import com.ihomefnt.aladdin.psi.controller.purchase.po.NPurchaseOrder;
import com.ihomefnt.aladdin.psi.deliverPurchase.constant.DeliverPurchaseOrderStatusConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 采购单处理
 */
@Service
public class PurchaseProcessor implements Processor {

    @Resource
    DeliverProductStatusRepository deliverProductStatusRepository;

    @Resource
    NPurchaseOrderDetailInfoRepository nPurchaseOrderDetailInfoRepository;

    @Resource
    NPurchaseOrderRepository nPurchaseOrderRepository;

    @Override
    public void process(ProductChangeContext context) {

        List<ChangeProductVo> changeProductVos = context.getChangeProductVos();
        for(ChangeProductVo changeProductVo:changeProductVos) {
            for (String deliverKey:changeProductVo.getDeliverKeys()) {
                DeliverProductStatus deliverProductStatus = deliverProductStatusRepository.findByDeliverKey(deliverKey);
                NPurchaseOrderDetailInfo nPurchaseOrderDetailInfo = nPurchaseOrderDetailInfoRepository.queryDetailByDeliverKey(deliverProductStatus.getDeliverKey());
                NPurchaseOrder nPurchaseOrder = nPurchaseOrderRepository.queryPurchaseOrderById(nPurchaseOrderDetailInfo.getPurchaseOrderId());
                BigDecimal orderAmount = nPurchaseOrder.getOrderProductAmount();
                BigDecimal orderPrice = nPurchaseOrder.getOrderPrice();
                orderAmount=orderAmount.subtract(deliverProductStatus.getPurchaseNum());
                orderPrice=orderPrice.subtract(deliverProductStatus.getPurchaseTotalPrice());
                //如果商品全退了就删除采购订单
                if (orderAmount.compareTo(BigDecimal.ZERO) == 0) {
                    nPurchaseOrder.setOrderStatus(DeliverPurchaseOrderStatusConstant.PURCHASE_CANCEL.getIndex());
                    nPurchaseOrder.setOrderPrice(orderPrice);
                    nPurchaseOrder.setOrderProductAmount(orderAmount);
                    nPurchaseOrderRepository.updateByPrimaryKeySelective(nPurchaseOrder);
                } else {
                    nPurchaseOrder.setOrderProductAmount(orderAmount);
                    nPurchaseOrder.setOrderPrice(orderPrice);
                    nPurchaseOrderRepository.updateByPrimaryKeySelective(nPurchaseOrder);
                }
                nPurchaseOrderDetailInfoRepository.deletePurchaseOrderDetailInfo(nPurchaseOrderDetailInfo.getId());
            }
        }
    }
}
