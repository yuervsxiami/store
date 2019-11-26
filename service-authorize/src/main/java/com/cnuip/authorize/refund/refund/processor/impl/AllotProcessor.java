package com.cnuip.authorize.refund.refund.processor.impl;

import com.ihomefnt.aladdin.dms.enums.ProductChangeApprovalConstant;
import com.ihomefnt.aladdin.dms.refund.dto.ProductChangeContext;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import com.ihomefnt.aladdin.dms.vo.purchase.request.ChangeProductVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 审批单拆单处理
 */
@Service
public class AllotProcessor implements Processor {

    @Override
    public void process(ProductChangeContext context) {
        if(CollectionUtils.isEmpty(context.getNeedLogisiticsProcess())){
            context.setNeedLogisiticsProcess(new ArrayList<>());
        }
        if(CollectionUtils.isEmpty(context.getNeedSellOff())){
            context.setNeedSellOff(new ArrayList<>());
        }
        if(CollectionUtils.isEmpty(context.getNeedDestory())){
            context.setNeedDestory(new ArrayList<>());
        }
        List<ChangeProductVo> changeProductVos = context.getChangeProductVos();
        for(ChangeProductVo changeProductVo:changeProductVos){
            if(ProductChangeApprovalConstant.DESTORY.equals(changeProductVo.getHandleMethod())){
                context.getNeedDestory().add(changeProductVo);
            }
            if(ProductChangeApprovalConstant.SELLOFF.equals(changeProductVo.getHandleMethod())){
                context.getNeedSellOff().add(changeProductVo);
            }

            // 0通知厂家不出货,7已生产，未到货，退货给厂家,8已到货，退至施工单位  三种情况不创建物流服务单。
            if(!(ProductChangeApprovalConstant.HANDLEMETHODNOSTOCK.equals(changeProductVo.getHandleMethod())
                || ProductChangeApprovalConstant.UNARRIVED_BACK_FACTORY.equals(changeProductVo.getHandleMethod())
                || ProductChangeApprovalConstant.PRODUCT_BACK_PROJECT.equals(changeProductVo.getHandleMethod()))
            ){
                context.getNeedLogisiticsProcess().add(changeProductVo);
            }
        }
    }
}
