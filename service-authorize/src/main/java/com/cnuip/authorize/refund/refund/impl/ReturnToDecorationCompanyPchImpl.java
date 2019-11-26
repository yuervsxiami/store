package com.cnuip.authorize.refund.refund.impl;

import com.ihomefnt.aladdin.dms.enums.DeliverProductStatusEnum;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 退至施工单位
 */
@Service
public class ReturnToDecorationCompanyPchImpl extends AbstractProductChangeHandleServiceImpl {
    @Override
    public List<Processor> getChangePurchaseProcessors(int status) {
        if (status >= DeliverProductStatusEnum.MANUFACTURER_SHIPMENT_COMPLETED.getCode()) {
            Arrays.asList(deliverProductProcessor,
                    financeWarehouseProcessor);
        }
        return null;
    }
}
