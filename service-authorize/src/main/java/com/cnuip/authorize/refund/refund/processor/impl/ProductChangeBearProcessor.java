package com.cnuip.authorize.refund.refund.processor.impl;

import com.ihomefnt.aladdin.dms.domain.ProductChangeBear;
import com.ihomefnt.aladdin.dms.enums.ProductChangeApprovalConstant;
import com.ihomefnt.aladdin.dms.refund.dto.ProductChangeContext;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import com.ihomefnt.aladdin.dms.repository.ProductChangeBearRepository;
import com.ihomefnt.aladdin.dms.vo.purchase.request.ProductChangeBearRequestVo;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:zhaozhihui
 * Date: 2019-11-20
 * Time: 15:58
 */
public class ProductChangeBearProcessor implements Processor {

    @Resource
    ProductChangeBearRepository productChangeBearRepository;

    @Override
    public void process(ProductChangeContext context) {
        if(context.getProductChangeLogisticRecord()!=null){
            return;
        }

        List<ProductChangeBearRequestVo> productChangeBearRequestVos = context.getProductChangeLogisticRecord().getProductChangeBears();
        if (null != context.getProductChangeLogisticRecord().getAllocationFlag()
                && ProductChangeApprovalConstant.ALLOCATIONFLAGYES.equals(context.getProductChangeLogisticRecord().getAllocationFlag())
                && !CollectionUtils.isEmpty(context.getProductChangeLogisticRecord().getProductChangeBears())) {
            List<ProductChangeBear> productChangeBearRecords = new ArrayList<>();
            ProductChangeBear productChangeBear = null;
            for (ProductChangeBearRequestVo productChangeBearRequestVo : productChangeBearRequestVos) {

                productChangeBear = new ProductChangeBear();
                productChangeBear.setChangeRecordId(context.getProductChangeLogisticRecord().getId());
                productChangeBear.setBearObject(productChangeBearRequestVo.getBearObject());
                productChangeBear.setObjectId(null == productChangeBearRequestVo.getObjectId() ? 0
                        : productChangeBearRequestVo.getObjectId());
                productChangeBear.setBearAmount(productChangeBearRequestVo.getBearAmount());
                productChangeBear.setCreateUserId(context.getOperatorId());
                productChangeBear.setDelFlag(0);

                productChangeBearRecords.add(productChangeBear);
            }

            if (!CollectionUtils.isEmpty(productChangeBearRecords)) {
                productChangeBearRepository.batchInsert(productChangeBearRecords);
            }
        }
    }
}
