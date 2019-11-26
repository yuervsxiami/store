package com.cnuip.authorize.refund.refund.processor.impl;

import com.ihomefnt.aladdin.ddd.dms.infrastructure.entity.TnProductChangeLogisticRecord;
import com.ihomefnt.aladdin.ddd.dms.infrastructure.entity.TnProductChangeRecordDetail;
import com.ihomefnt.aladdin.ddd.dms.infrastructure.entity.TnProductLogisticRecordDetail;
import com.ihomefnt.aladdin.ddd.dms.infrastructure.mapper.TnProductChangeLogisticRecordMapper;
import com.ihomefnt.aladdin.ddd.dms.infrastructure.mapper.TnProductChangeRecordDetailMapper;
import com.ihomefnt.aladdin.ddd.dms.infrastructure.mapper.TnProductLogisticRecordDetailMapper;
import com.ihomefnt.aladdin.dms.domain.ApprovalRecord;
import com.ihomefnt.aladdin.dms.domain.ProductChangeApproval;
import com.ihomefnt.aladdin.dms.domain.ProductChangeRecord;
import com.ihomefnt.aladdin.dms.enums.ApprovalRecordConstant;
import com.ihomefnt.aladdin.dms.enums.ProductChangeOperationCodeEnum;
import com.ihomefnt.aladdin.dms.refund.dto.ProductChangeContext;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import com.ihomefnt.aladdin.dms.repository.ApprovalRecordRepository;
import com.ihomefnt.aladdin.dms.repository.LogisticDealRecordRepository;
import com.ihomefnt.aladdin.dms.repository.ProductChangeApprovalRepository;
import com.ihomefnt.aladdin.dms.service.WarehouseProductService;
import com.ihomefnt.aladdin.dms.vo.purchase.request.ChangeProductVo;
import com.ihomefnt.common.util.ModelMapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User:zhaozhihui
 * Date: 2019-11-20
 * Time: 14:55
 */

@Service
public class SaveSellOffProductProcessor implements Processor {

    @Resource
    LogisticDealRecordRepository logisticDealRecordRepository;

    @Resource
    ProductChangeApprovalRepository productChangeApprovalRepository;

    @Resource
    TnProductChangeRecordDetailMapper tnProductChangeRecordDetailMapper;

    @Resource
    TnProductLogisticRecordDetailMapper tnProductLogisticRecordDetailMapper;

    @Resource
    WarehouseProductService warehouseProductService;

    @Resource
    ApprovalRecordRepository approvalRecordRepository;

    @Resource
    TnProductChangeLogisticRecordMapper tnProductChangeLogisticRecordMapper;


    @Override
    public void process(ProductChangeContext context) {
        ProductChangeRecord productChangeRecord = context.getProductChangeRecord();
        if (CollectionUtils.isEmpty(context.getNeedSellOff()) || productChangeRecord == null) {
            return;
        }
        //对销毁的商品进行处理
        TnProductChangeLogisticRecord logisticDealRecord = ModelMapperUtil.strictMap(productChangeRecord,TnProductChangeLogisticRecord.class);
        logisticDealRecord.setType(1);
        tnProductChangeLogisticRecordMapper.insertTnProductChangeLogisticRecord(logisticDealRecord);
        //保存审批表
        ProductChangeApproval productChangeApprovalRecord = new ProductChangeApproval();
        productChangeApprovalRecord.setChangeRecordId(logisticDealRecord.getId());
        productChangeApprovalRecord.setOperationCode(ProductChangeOperationCodeEnum.APPROVE.getCode());
        productChangeApprovalRecord.setCreateUserId(context.getOperatorId());
        productChangeApprovalRecord.setDelFlag(0);
        productChangeApprovalRecord.setOperationCode(ProductChangeOperationCodeEnum.IN_APPROVE.getCode());
        productChangeApprovalRepository.insertSelective(productChangeApprovalRecord);

        ApprovalRecord record = new ApprovalRecord();
        record.setSource(ApprovalRecordConstant.RETURN_OPERATOR);
        record.setApprovalStatus(ApprovalRecordConstant.IN_APPROVAL);
        record.setRelateId(productChangeApprovalRecord.getId());
        record.setCreateUserId(context.getOperatorId());
        record.setBomType(1);
        warehouseProductService.createApproval(context.getOperatorId().intValue(), context.getNeedDestory().get(0).getHandleReason(), record);
        record.setApprovalType(context.getNeedDestory().get(0).getHandleMethod());
        approvalRecordRepository.insertSelective(record);
        for(ChangeProductVo changeProductVo:context.getNeedSellOff()){
            for(String deliverKey:changeProductVo.getDeliverKeys()){
                TnProductChangeRecordDetail tnProductChangeRecordDetail = tnProductChangeRecordDetailMapper.queryProductChangeRecordDetailByDeliverKey(deliverKey);
                TnProductLogisticRecordDetail detail = ModelMapperUtil.strictMap(tnProductChangeRecordDetail, TnProductLogisticRecordDetail.class);
                detail.setChangeRecordId(logisticDealRecord.getId());
                tnProductLogisticRecordDetailMapper.insertTnProductLogisticRecordDetail(detail);
            }
        }
    }
}
