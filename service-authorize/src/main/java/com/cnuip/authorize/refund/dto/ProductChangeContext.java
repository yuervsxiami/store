package com.cnuip.authorize.refund.dto;

import com.ihomefnt.aladdin.ddd.dms.infrastructure.entity.TnProductChangeLogisticRecord;
import com.ihomefnt.aladdin.dms.domain.ProductChangeRecord;
import com.ihomefnt.aladdin.dms.vo.purchase.request.ChangeProductVo;
import lombok.Data;

import java.util.List;

@Data
public class ProductChangeContext {

    private Long operatorId;

    private Long changeRecordId;

    private Long orderId;

    private Integer afterSaleType;

    private Integer changeType;

    private ProductChangeRecord productChangeRecord;

    // 变更详情
    private List<ChangeProductVo> changeProductVos;

    // 物流
    private List<ChangeProductVo> needLogisiticsProcess;

    // 变卖
    private List<ChangeProductVo> needSellOff;

    // 销毁
    private List<ChangeProductVo> needDestory;

    //拆单后物流
    private TnProductChangeLogisticRecord productChangeLogisticRecord;

}
