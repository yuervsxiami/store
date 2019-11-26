package com.cnuip.authorize.refund.refund.processor.impl;

import com.ihomefnt.aladdin.dms.dto.financial.FinanceWarehouseProducrChangeDto;
import com.ihomefnt.aladdin.dms.enums.finance.FinanceWarehouseTypeEnum;
import com.ihomefnt.aladdin.dms.refund.dto.ProductChangeContext;
import com.ihomefnt.aladdin.dms.refund.service.Processor;
import com.ihomefnt.aladdin.dms.service.impl.finance.FinanceWarehouseFactory;
import com.ihomefnt.aladdin.dms.vo.purchase.request.ChangeProductVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 财务库存处理
 */
@Service
public class FinanceWarehouseProcessor implements Processor {
    @Resource
    private FinanceWarehouseFactory financeWarehouseFactory;

    @Override
    public void process(ProductChangeContext context) {
        if (CollectionUtils.isEmpty(context.getChangeProductVos())) {
            return;
        }

        Map<Integer, List<ChangeProductVo>> changeProductVoMap = context.getChangeProductVos().stream().collect(Collectors.groupingBy(ChangeProductVo::getHandleMethod));

        for (Map.Entry<Integer, List<ChangeProductVo>> entry : changeProductVoMap.entrySet()) {
            FinanceWarehouseProducrChangeDto param = new FinanceWarehouseProducrChangeDto();
            param.setType(FinanceWarehouseTypeEnum.NEW_DELIVER_PRODUCT_CHANGE);
            param.setOperator(context.getOperatorId());
            param.setChangeProductVos(entry.getValue());
            param.setAfterSaleType(context.getAfterSaleType());
            param.setChangeRecordId(context.getChangeRecordId());
            param.setOrderId(context.getOrderId());
            financeWarehouseFactory.doWarehouse(param);
        }

    }
}
