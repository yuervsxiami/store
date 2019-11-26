package com.cnuip.authorize.refund.refund.processor.impl;

import com.ihomefnt.aladdin.ddd.dms.infrastructure.entity.TnProductChangeRecordDetail;
import com.ihomefnt.aladdin.ddd.dms.infrastructure.mapper.TnProductChangeRecordDetailMapper;
import com.ihomefnt.aladdin.dms.refund.dto.ProductChangeContext;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import com.ihomefnt.aladdin.dms.vo.purchase.request.ChangeProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 保存处理方式
 */
@Service
public class SaveHandleMethodProcessor implements Processor {


    @Autowired
    TnProductChangeRecordDetailMapper tnProductChangeRecordDetailMapper;

    @Override
    public void process(ProductChangeContext context) {
        if (CollectionUtils.isEmpty(context.getChangeProductVos())) {
            return;
        }
        List<ChangeProductVo> changeProductVos = context.getChangeProductVos();
        for(ChangeProductVo changeProductVo:changeProductVos){
            List<String> deliverKeys = changeProductVo.getDeliverKeys();
            TnProductChangeRecordDetail detail=new TnProductChangeRecordDetail();
            detail.setDeliverKeys(deliverKeys);
            detail.setHandleMethod(changeProductVo.getHandleMethod());
            detail.setHandleReason(changeProductVo.getHandleReason());
            detail.setSelloffPrice(changeProductVo.getSelloffPrice());
            tnProductChangeRecordDetailMapper.updateHandleMethodByDeliverKeys(detail);
        }
    }
}
