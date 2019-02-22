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
@ApiModel(value = "专利收藏查询实体", description = "专利收藏查询实体")
public class FavoriteParam extends QueryParam {

    /** mbr_user.id */
    @ApiModelProperty(value="", name="userId", dataType="Long")
    private Long userId;
    public void setUserId(Long userId) { this.userId = userId; } 
    public Long getUserId() { return this.userId; } 

    /** 专利类型 [ INVENTION.发明专利 APPEARANCE.外观专利 UTILITY.实用新型 ] */
    /** PatentTypeEnum */
    @ApiModelProperty(value="专利类型 [ INVENTION.发明专利 APPEARANCE.外观专利 UTILITY.实用新型 ]", name="patentType", dataType="String", example="INVENTION")
    private String patentType;
    public void setPatentType(String patentType) { this.patentType = patentType; } 
    public String getPatentType() { return this.patentType; } 

    /** 申请号 */
    @ApiModelProperty(value="申请号", name="an", dataType="String")
    private String an;
    public void setAn(String an) { this.an = an; } 
    public String getAn() { return this.an; } 

    /** 名称 */
    @ApiModelProperty(value="名称", name="ti", dataType="String")
    private String ti;
    public void setTi(String ti) { this.ti = ti; } 
    public String getTi() { return this.ti; } 

    /** 申请人 */
    @ApiModelProperty(value="申请人", name="pa", dataType="String")
    private String pa;
    public void setPa(String pa) { this.pa = pa; } 
    public String getPa() { return this.pa; } 

    /** 发明人 */
    @ApiModelProperty(value="发明人", name="pin", dataType="String")
    private String pin;
    public void setPin(String pin) { this.pin = pin; } 
    public String getPin() { return this.pin; } 

    /** 状态 */
    @ApiModelProperty(value="状态", name="state", dataType="String")
    private String state;
    public void setState(String state) { this.state = state; } 
    public String getState() { return this.state; } 

}
