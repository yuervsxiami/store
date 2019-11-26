package com.cnuip.authorize.refund.service;

import com.ihomefnt.aladdin.dms.refund.dto.ProductChangeContext;

public interface Processor {
    void process(ProductChangeContext context);
}
