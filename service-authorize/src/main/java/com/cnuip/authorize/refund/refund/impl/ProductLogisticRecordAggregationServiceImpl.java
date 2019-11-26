package com.cnuip.authorize.refund.refund.impl;

import com.ihomefnt.aladdin.ddd.dms.infrastructure.entity.TnProductLogisticRecordDetail;
import com.ihomefnt.aladdin.ddd.dms.infrastructure.mapper.TnProductLogisticRecordDetailMapper;
import com.ihomefnt.aladdin.dms.refund.service.ProductLogisticRecordAggregationService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductLogisticRecordAggregationServiceImpl implements ProductLogisticRecordAggregationService {
    @Resource
    private TnProductLogisticRecordDetailMapper tnProductLogisticRecordDetailMapper;

    @Override
    public TnProductLogisticRecordDetail getById(Long id) {
        TnProductLogisticRecordDetail tnProductLogisticRecordDetail = tnProductLogisticRecordDetailMapper.selectById(id);
        if (tnProductLogisticRecordDetail == null) {
            return null;
        }
        // 根据changeRecordid、superkey、状态查询同批处理信息
        List<TnProductLogisticRecordDetail> recordDetails = tnProductLogisticRecordDetailMapper.selectByChangeRecordIdAndSuperKey(tnProductLogisticRecordDetail.getChangeRecordId(),tnProductLogisticRecordDetail.getSuperKey(),tnProductLogisticRecordDetail.getProductStatus());
        if (CollectionUtils.isEmpty(recordDetails)) {
            return null;
        }

        List<String> deliverKeys = new ArrayList<>();
        for (TnProductLogisticRecordDetail recordDetail : recordDetails) {
            deliverKeys.add(recordDetail.getDeliverKey());
        }
        tnProductLogisticRecordDetail.setDeliverKeys(deliverKeys);
        return tnProductLogisticRecordDetail;
    }
}
