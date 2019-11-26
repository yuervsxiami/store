package com.cnuip.authorize.refund.refund.processor.impl;

import com.ihomefnt.aladdin.ddd.dms.application.InstallOrderApplicationService;
import com.ihomefnt.aladdin.dms.refund.dto.ProductChangeContext;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 安装单处理
 */
@Service
public class InstallOrderProcessor implements Processor {

    @Autowired
    private InstallOrderApplicationService installOrderApplicationService;

    @Override
    public void process(ProductChangeContext context) {
        if (CollectionUtils.isEmpty(context.getChangeProductVos())) {
            return;
        }

        List<String> deliverKeys = new ArrayList<>();
        context.getChangeProductVos().forEach(item -> {
            if (!CollectionUtils.isEmpty(item.getDeliverKeys())) {
                deliverKeys.addAll(item.getDeliverKeys());
            }
        });

        installOrderApplicationService.returnProduct(deliverKeys);
    }
}
