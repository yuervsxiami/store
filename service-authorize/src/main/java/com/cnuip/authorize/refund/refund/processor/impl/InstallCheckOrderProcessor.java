package com.cnuip.authorize.refund.refund.processor.impl;

import com.ihomefnt.aladdin.ddd.dms.application.InstallCheckOrderApplicationService;
import com.ihomefnt.aladdin.dms.refund.dto.ProductChangeContext;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import com.ihomefnt.aladdin.dms.vo.purchase.request.ChangeProductVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 安装验收单处理
 */
@Service
public class InstallCheckOrderProcessor implements Processor {
    @Resource
    private InstallCheckOrderApplicationService installCheckOrderApplicationService;

    @Override
    public void process(ProductChangeContext context) {
        if (CollectionUtils.isEmpty(context.getChangeProductVos())) {
            return;
        }

        List<String> deliverKeys = new ArrayList<>();
        for (ChangeProductVo changeProductVo : context.getChangeProductVos()) {
            if (!CollectionUtils.isEmpty(changeProductVo.getDeliverKeys())) {
                deliverKeys.addAll(changeProductVo.getDeliverKeys());
            }
        }
        installCheckOrderApplicationService.returnGoods(deliverKeys, context.getOrderId());
    }
}
