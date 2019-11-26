package com.cnuip.authorize.refund.refund.impl;

import com.ihomefnt.aladdin.dms.refund.service.Processor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 变卖
 */
@Service
public class SellOffPchImpl extends AbstractProductChangeHandleServiceImpl {
    @Resource
    private Processor allotProcessor;
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
