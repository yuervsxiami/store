package com.cnuip.authorize.refund.refund.processor.impl;

import com.ihomefnt.aladdin.ddd.dms.infrastructure.entity.TnProductChangeLogisticRecord;
import com.ihomefnt.aladdin.ddd.dms.infrastructure.entity.TnProductChangeRecordDetail;
import com.ihomefnt.aladdin.ddd.dms.infrastructure.entity.TnProductLogisticRecordDetail;
import com.ihomefnt.aladdin.ddd.dms.infrastructure.mapper.TnLogisticDealDetailMapper;
import com.ihomefnt.aladdin.ddd.dms.infrastructure.mapper.TnProductChangeLogisticRecordMapper;
import com.ihomefnt.aladdin.ddd.dms.infrastructure.mapper.TnProductChangeRecordDetailMapper;
import com.ihomefnt.aladdin.ddd.dms.infrastructure.mapper.TnProductLogisticRecordDetailMapper;
import com.ihomefnt.aladdin.dms.domain.ProductChangeApproval;
import com.ihomefnt.aladdin.dms.domain.ProductChangeRecord;
import com.ihomefnt.aladdin.dms.enums.ProductChangeOperationCodeEnum;
import com.ihomefnt.aladdin.dms.refund.dto.ProductChangeContext;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import com.ihomefnt.aladdin.dms.repository.ProductChangeApprovalRepository;
import com.ihomefnt.aladdin.dms.service.ProductChangeTaskService;
import com.ihomefnt.aladdin.dms.vo.purchase.request.ChangeProductVo;
import com.ihomefnt.common.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Created by IntelliJ IDEA.
 * User:zhaozhihui
 * Date: 2019-11-20
 * Time: 14:16
 */

@Service
public class SaveNeedLogisticProductProcessor implements Processor {

    @Autowired
    TnProductChangeLogisticRecordMapper tnProductChangeLogisticRecordMapper;

    @Autowired
    ProductChangeApprovalRepository productChangeApprovalRepository;

    @Autowired
    TnProductChangeRecordDetailMapper tnProductChangeRecordDetailMapper;

    @Autowired
    TnLogisticDealDetailMapper tnLogisticDealDetailMapper;

    @Autowired
    ProductChangeTaskService productChangeTaskService;

    @Autowired
    TnProductLogisticRecordDetailMapper tnProductLogisticRecordDetailMapper;


    @Override
    public void process(ProductChangeContext context) {
        ProductChangeRecord productChangeRecord = context.getProductChangeRecord();
        if (CollectionUtils.isEmpty(context.getNeedLogisiticsProcess()) || productChangeRecord == null) {
            return;
        }
        TnProductChangeLogisticRecord logisticDealRecord = ModelMapperUtil.strictMap(productChangeRecord,TnProductChangeLogisticRecord.class);
        logisticDealRecord.setType(0);
        tnProductChangeLogisticRecordMapper.insertTnProductChangeLogisticRecord(logisticDealRecord);
        //保存审批表
        ProductChangeApproval productChangeApprovalRecord = new ProductChangeApproval();
        productChangeApprovalRecord.setChangeRecordId(logisticDealRecord.getId());
        productChangeApprovalRecord.setOperationCode(ProductChangeOperationCodeEnum.APPROVE.getCode());
        productChangeApprovalRecord.setCreateUserId(context.getOperatorId());
        productChangeApprovalRecord.setDelFlag(0);
        productChangeApprovalRepository.insertSelective(productChangeApprovalRecord);

        for(ChangeProductVo changeProductVo:context.getNeedLogisiticsProcess()){
            for(String deliverKey:changeProductVo.getDeliverKeys()){
                TnProductChangeRecordDetail tnProductChangeRecordDetail = tnProductChangeRecordDetailMapper.queryProductChangeRecordDetailByDeliverKey(deliverKey);
                TnProductLogisticRecordDetail detail = ModelMapperUtil.strictMap(tnProductChangeRecordDetail, TnProductLogisticRecordDetail.class);
                detail.setChangeRecordId(logisticDealRecord.getId());
                tnProductLogisticRecordDetailMapper.insertTnProductLogisticRecordDetail(detail);
            }
        }

        //待物流处理的需要创建商品变更处理交给物流人员处理
        productChangeTaskService.newlaunchProductChangeLogisticsTask(logisticDealRecord);
    }
}
