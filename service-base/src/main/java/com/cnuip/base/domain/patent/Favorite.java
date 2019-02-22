package com.cnuip.base.domain.patent;

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
@ApiModel(value = "专利收藏", description = "专利收藏")
public class Favorite extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** mbr_user.id */
    @ApiModelProperty(value="", name="userId", dataType="Long")
    private Long userId;

    /** 专利类型 [ INVENTION.发明专利 APPEARANCE.外观专利 UTILITY.实用新型 ] */
    /** PatentTypeEnum */
    @ApiModelProperty(value="专利类型 [ INVENTION.发明专利 APPEARANCE.外观专利 UTILITY.实用新型 ]", name="patentType", dataType="String", example="INVENTION")
    private String patentType;

    /** 申请号 */
    @ApiModelProperty(value="申请号", name="an", dataType="String")
    private String an;

    /** 名称 */
    @ApiModelProperty(value="名称", name="ti", dataType="String")
    private String ti;

    /** 申请人 */
    @ApiModelProperty(value="申请人", name="pa", dataType="String")
    private String pa;

    /** 发明人 */
    @ApiModelProperty(value="发明人", name="pin", dataType="String")
    private String pin;

    /** 状态 */
    @ApiModelProperty(value="状态", name="state", dataType="String")
    private String state;


    @Override
    public String checkValue(){

        if (StringUtils.isNullOrEmpty(this.getPatentType())){
            return "FAVORITE_PATENT_TYPE_CANNOT_NULL";
        }
        if (this.getPatentType() == null){
            return "FAVORITE_PATENT_TYPE_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getAn()) > 255){
            return "FAVORITE_AN_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getTi()) > 500){
            return "FAVORITE_TI_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getPa()) > 500){
            return "FAVORITE_PA_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getPin()) > 1000){
            return "FAVORITE_PIN_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getState()) > 50){
            return "FAVORITE_STATE_MAX_LENGTH_ERROR";
        }

        return null;
    }

    @Override
    public Favorite setDefaultValue(){

        if (StringUtils.isNullOrEmpty(this.getPatentType())){
            this.setPatentType("INVENTION");
        }

        return this;
    }
}
