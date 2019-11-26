package com.cnuip.authorize.refund.refund.impl;

import com.ihomefnt.aladdin.dms.enums.DeliverProductStatusEnum;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 退至物流
 */
@Service
public class ReturnToLogisticsPchImpl extends AbstractProductChangeHandleServiceImpl {
    @Override
    public List<Processor> getChangePurchaseProcessors(int status) {
        if (status >= DeliverProductStatusEnum.WAITING_ORDERS.getCode()) {
            return Arrays.asList(deliverProductProcessor,
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
