package com.cnuip.base.domain.authorize;

import com.cnuip.base.base.BaseModel;
import com.cnuip.base.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Data
@ApiModel(value = "委托专利表", description = "委托专利表")
public class AuthorizePatent extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** aut_authorize.id */
    @ApiModelProperty(value="", name="authorizeId", dataType="Long")
    private Long authorizeId;

    /** 类型 [ INVENTION.发明专利 APPEARANCE.外观专利 UTILITY.实用新型 ] */
    /** PatentTypeEnum */
    @ApiModelProperty(value="类型 [ INVENTION.发明专利 APPEARANCE.外观专利 UTILITY.实用新型 ]", name="type", dataType="String")
    private String type;

    /** 专利唯一ID */
    @ApiModelProperty(value="专利唯一ID", name="sysId", dataType="String")
    private String sysId;

    /** 申请号 */
    @ApiModelProperty(value="申请号", name="an", dataType="String")
    private String an;

    /** 申请日 */
    @ApiModelProperty(value="申请日", name="ad", dataType="java.util.Date")
    private java.util.Date ad;

    /** 申请人 */
    @ApiModelProperty(value="申请人", name="pa", dataType="String")
    private String pa;

    /** 公开（公告）号 */
    @ApiModelProperty(value="公开（公告）号", name="pnm", dataType="String")
    private String pnm;

    /** 名称 */
    @ApiModelProperty(value="名称", name="ti", dataType="String")
    private String ti;

    /** 发明人 */
    @ApiModelProperty(value="发明人", name="pin", dataType="String")
    private String pin;

    /** 委托价格 */
    @ApiModelProperty(value="委托价格", name="price", dataType="java.math.BigDecimal", example="10000.00")
    private java.math.BigDecimal price;

    @ApiModelProperty(value="交易类型 [ TRANSFER.转让 LICENSE.许可 ]", name="tradeType", dataType="String", example="TRANSFER")
    private String tradeType;

    @Override
    public String checkValue(){

        if (StringUtils.isNullOrEmpty(this.getType())){
            return "AUTHORIZEPATENT_TYPE_CANNOT_NULL";
        }
        if (this.getType() == null){
            return "AUTHORIZEPATENT_TYPE_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getSysId()) > 255){
            return "AUTHORIZEPATENT_SYS_ID_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getAn())){
            return "AUTHORIZEPATENT_AN_CANNOT_NULL";
        }
        if (this.getAn() == null){
            return "AUTHORIZEPATENT_AN_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getAn()) > 255){
            return "AUTHORIZEPATENT_AN_MAX_LENGTH_ERROR";
        }
        if (this.getAd() == null){
            return "AUTHORIZEPATENT_AD_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getPa())){
            return "AUTHORIZEPATENT_PA_CANNOT_NULL";
        }
        if (this.getPa() == null){
            return "AUTHORIZEPATENT_PA_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getPa()) > 500){
            return "AUTHORIZEPATENT_PA_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getPnm())){
            return "AUTHORIZEPATENT_PNM_CANNOT_NULL";
        }
        if (this.getPnm() == null){
            return "AUTHORIZEPATENT_PNM_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getPnm()) > 255){
            return "AUTHORIZEPATENT_PNM_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getTi())){
            return "AUTHORIZEPATENT_TI_CANNOT_NULL";
        }
        if (this.getTi() == null){
            return "AUTHORIZEPATENT_TI_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getTi()) > 500){
            return "AUTHORIZEPATENT_TI_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getPin())){
            return "AUTHORIZEPATENT_PIN_CANNOT_NULL";
        }
        if (this.getPin() == null){
            return "AUTHORIZEPATENT_PIN_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getPin()) > 1000){
            return "AUTHORIZEPATENT_PIN_MAX_LENGTH_ERROR";
        }
        if (this.getPrice() == null){
            return "AUTHORIZEPATENT_PRICE_CANNOT_NULL";
        }
        if (this.getTradeType() == null){
            return "AUTHORIZEPATENT_TRADETYPE_CANNOT_NULL";
        }
        return null;
    }

    @Override
    public AuthorizePatent setDefaultValue(){

        if (this.getPrice() == null){
            this.setPrice(new java.math.BigDecimal(0.00));
        }

        return this;
    }
}
