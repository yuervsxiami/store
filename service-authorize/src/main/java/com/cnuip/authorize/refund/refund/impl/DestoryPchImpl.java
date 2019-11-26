package com.cnuip.authorize.refund.refund.impl;

import com.ihomefnt.aladdin.dms.refund.service.Processor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 销毁
 */
@Service
public class DestoryPchImpl extends AbstractProductChangeHandleServiceImpl {


    @Override
    public List<Processor> getChangePurchaseProcessors(int status) {
        return Arrays.asList(allotProcessor);
    }

    @Override
    public List<Processor> getApprovalPass(int status) {
        return Arrays.asList(deliverProductProcessor,
                logisticsPlanProcessor,
                logisticsProcessor,
                financeWarehouseProcessor,
                installOrderProcessor,
                installCheckOrderProcessor);
    }
}
