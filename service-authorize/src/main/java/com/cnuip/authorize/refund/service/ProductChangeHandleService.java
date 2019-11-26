package com.cnuip.authorize.refund.service;

import com.ihomefnt.aladdin.dms.refund.dto.ProductChangeContext;

public interface ProductChangeHandleService {
    /**
     * 采购处理
     * @param context
     */
    void changePurchaseInfo(ProductChangeContext context);

    /**
     * 物流处理
     * @param context
     */
    void changeLogisticInfo(ProductChangeContext context);

    /**
     * 服务订单完成
     * @param context
     */
    void finishServiceOrder(ProductChangeContext context);

    /**
     * 审批通过处理
     * @param context
     */
    void approvalPass(ProductChangeContext context);
}
