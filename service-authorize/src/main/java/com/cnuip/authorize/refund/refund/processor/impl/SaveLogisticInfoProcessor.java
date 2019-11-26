package com.cnuip.authorize.refund.refund.processor.impl;

import com.ihomefnt.aladdin.ddd.dms.infrastructure.entity.TnProductLogisticRecordDetail;
import com.ihomefnt.aladdin.ddd.dms.infrastructure.mapper.TnProductLogisticRecordDetailMapper;
import com.ihomefnt.aladdin.dms.refund.dto.ProductChangeContext;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import com.ihomefnt.aladdin.dms.vo.purchase.request.ChangeProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 物流保存
 */
@Service
@Slf4j
public class SaveLogisticInfoProcessor implements Processor {

    @Autowired
    TnProductLogisticRecordDetailMapper tnProductLogisticRecordDetailMapper;

    @Override
    public void process(ProductChangeContext context) {
        if (CollectionUtils.isEmpty(context.getNeedLogisiticsProcess())) {
            return;
        }
        for(ChangeProductVo vo:context.getNeedLogisiticsProcess()){
            if(vo.getServiceId()==null){
                log.info("SaveLogisticInfoProcessor serviceId is null");
                continue;
            }
            for(String deliverKey:vo.getDeliverKeys()){
                TnProductLogisticRecordDetail detail=new TnProductLogisticRecordDetail();
                detail.setDeliverKey(deliverKey);
                detail.setServiceId(vo.getServiceId());
                detail.setWarehouseNo(vo.getWareHouseNo());
                detail.setCargoLabel(vo.getCargoLabel());
                tnProductLogisticRecordDetailMapper.updateTnProductLogisticRecordDetailByDeliverKey(detail);
            }
        }

    }
}
