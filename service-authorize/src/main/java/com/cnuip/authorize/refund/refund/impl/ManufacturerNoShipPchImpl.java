package com.cnuip.authorize.refund.refund.impl;

import com.ihomefnt.aladdin.dms.enums.DeliverProductStatusEnum;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 通知厂家不出货处理
 */
@Service
public class ManufacturerNoShipPchImpl extends AbstractProductChangeHandleServiceImpl {
    @Override
    public List<Processor> getChangePurchaseProcessors(int status) {
        if (status == DeliverProductStatusEnum.WAITING_PURCHASE.getCode()) {
            return Arrays.asList(deliverProductProcessor,
                    installCheckOrderProcessor);
        } else if (status == DeliverProductStatusEnum.WAITING_MANUFACTURER_RECEIVE.getCode()) {
            return Arrays.asList(deliverProductProcessor,
                    purchaseProcessor,
                    installCheckOrderProcessor);
        } else if (status == DeliverProductStatusEnum.WAITING_MANUFACTURER_SHIPMENT.getCode()) {
            return Arrays.asList(financePaybillProcessor,
                    deliverProductProcessor,
                    purchaseProcessor,
                    installCheckOrderProcessor);
        }
        return null;
    }

}
