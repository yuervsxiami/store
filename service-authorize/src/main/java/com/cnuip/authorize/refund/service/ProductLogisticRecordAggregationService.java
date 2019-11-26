package com.cnuip.authorize.refund.service;

import com.ihomefnt.aladdin.ddd.dms.infrastructure.entity.TnProductLogisticRecordDetail;

public interface ProductLogisticRecordAggregationService {
    /**
     * 根据商品变更明细id，查询同单据同superKey同状态的商品变更记录聚合deliverKeys
     * @param id
     * @return
     */

    TnProductLogisticRecordDetail getById(Long id);
}
