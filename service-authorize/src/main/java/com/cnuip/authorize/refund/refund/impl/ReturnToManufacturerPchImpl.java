package com.cnuip.authorize.refund.refund.impl;

import com.ihomefnt.aladdin.dms.enums.DeliverProductStatusEnum;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 退至厂家
 */
@Service
public class ReturnToManufacturerPchImpl extends AbstractProductChangeHandleServiceImpl {
    @Override
    public List<Processor> getChangePurchaseProcessors(int status) {
        if (status == DeliverProductStatusEnum.WAITING_MANUFACTURER_SHIPMENT.getCode()) {
            return Arrays.asList(financePaybillProcessor,
                    deliverProductProcessor,
                    purchaseProcessor,
                    installCheckOrderProcessor
                    );
        } else if (status > DeliverProductStatusEnum.WAITING_MANUFACTURER_SHIPMENT.getCode() && status < DeliverProductStatusEnum.WAITING_ORDERS.getCode()) {
            return Arrays.asList(financePaybillProcessor,
                    deliverProductProcessor,
                    purchaseProcessor,
                    logisticsPlanProcessor,
                    logisticsProcessor,
                    installCheckOrderProcessor,
                    allotProcessor);
        } else if (status >= DeliverProductStatusEnum.WAITING_ORDERS.getCode()) {
            return Arrays.asList(financePaybillProcessor,
                    deliverProductProcessor,
                    purchaseProcessor,
                    allotProcessor);
        }
        return null;
    }

    @Override
    public List<Processor> getChangeLogisticProcessors(int status) {
        return Arrays.asList(serviceOrderProcessor);
    }

    @Override
    public List<Processor> getFinishServiceOrderProcessors(int status) {
        return Arrays.asList(financeWarehouseProcessor,
                installOrderProcessor,
                installCheckOrderProcessor);
    }
}
