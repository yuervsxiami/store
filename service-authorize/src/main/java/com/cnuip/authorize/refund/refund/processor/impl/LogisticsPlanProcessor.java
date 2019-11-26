package com.cnuip.authorize.refund.refund.processor.impl;

import com.ihomefnt.aladdin.ddd.dms.infrastructure.repository.TnLogisticsPlanRepository;
import com.ihomefnt.aladdin.dms.refund.dto.ProductChangeContext;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 物流计划处理
 */
@Slf4j
@Service
public class LogisticsPlanProcessor implements Processor {

    @Autowired
    private TnLogisticsPlanRepository logisticsPlanRepository;

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

        logisticsPlanRepository.delLogisticPlanByDeliverKeys(deliverKeys);

    }
}
