package com.cnuip.base.domain.params;

import com.cnuip.base.base.QueryParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "委托专利表查询实体", description = "委托专利表查询实体")
public class AuthorizePatentParam extends QueryParam {

    /** aut_authorize.id */
    @ApiModelProperty(value="", name="authorizeId", dataType="Long")
    private Long authorizeId;
    public void setAuthorizeId(Long authorizeId) { this.authorizeId = authorizeId; } 
    public Long getAuthorizeId() { return this.authorizeId; } 

    /** 类型 [ INVENTION.发明专利 APPEARANCE.外观专利 UTILITY.实用新型 ] */
    /** PatentTypeEnum */
    @ApiModelProperty(value="类型 [ INVENTION.发明专利 APPEARANCE.外观专利 UTILITY.实用新型 ]", name="type", dataType="String")
    private String type;
    public void setType(String type) { this.type = type; } 
    public String getType() { return this.type; } 

    /** 专利唯一ID */
    @ApiModelProperty(value="专利唯一ID", name="sysId", dataType="String")
    private String sysId;
    public void setSysId(String sysId) { this.sysId = sysId; } 
    public String getSysId() { return this.sysId; } 

    /** 申请号 */
    @ApiModelProperty(value="申请号", name="an", dataType="String")
    private String an;
    public void setAn(String an) { this.an = an; } 
    public String getAn() { return this.an; } 

    /** 申请日 */
    @ApiModelProperty(value="申请日", name="ad", dataType="java.util.Date")
    private java.util.Date adFrom;
    public void setAdFrom(java.util.Date adFrom) { this.adFrom = adFrom; } 
    public java.util.Date getAdFrom() { return this.adFrom; } 
    private java.util.Date adTo;
    public void setAdTo(java.util.Date adTo) { this.adTo = adTo; } 
    public java.util.Date getAdTo() { return this.adTo; } 

    /** 申请人 */
    @ApiModelProperty(value="申请人", name="pa", dataType="String")
    private String pa;
    public void setPa(String pa) { this.pa = pa; } 
    public String getPa() { return this.pa; } 

    /** 公开（公告）号 */
    @ApiModelProperty(value="公开（公告）号", name="pnm", dataType="String")
    private String pnm;
    public void setPnm(String pnm) { this.pnm = pnm; } 
    public String getPnm() { return this.pnm; } 

    /** 名称 */
    @ApiModelProperty(value="名称", name="ti", dataType="String")
    private String ti;
    public void setTi(String ti) { this.ti = ti; } 
    public String getTi() { return this.ti; } 

    /** 发明人 */
    @ApiModelProperty(value="发明人", name="pin", dataType="String")
    private String pin;
    public void setPin(String pin) { this.pin = pin; } 
    public String getPin() { return this.pin; } 

    /** 委托价格 */
    @ApiModelProperty(value="委托价格", name="price", dataType="java.math.BigDecimal", example="10000.00")
    private java.math.BigDecimal priceFrom;
    public void setPriceFrom(java.math.BigDecimal priceFrom) { this.priceFrom = priceFrom; } 
    public java.math.BigDecimal getPriceFrom() { return this.priceFrom; } 
    private java.math.BigDecimal priceTo;
    public void setPriceTo(java.math.BigDecimal priceTo) { this.priceTo = priceTo; } 
    public java.math.BigDecimal getPriceTo() { return this.priceTo; }
    /** 交易类型 */
    @ApiModelProperty(value="交易类型", name="tradeType", dataType="String", example="TRANSFER")
    private String tradeType;
    public String getTradeType() {
        return tradeType;
    }
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
}
