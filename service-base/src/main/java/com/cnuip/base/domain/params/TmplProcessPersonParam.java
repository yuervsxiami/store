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
@ApiModel(value = "流程模版对应抄送人查询实体", description = "流程模版对应抄送人查询实体")
public class TmplProcessPersonParam extends QueryParam {

    /** 流程模版ID pro_tmpl_process.id */
    @ApiModelProperty(value="流程模版ID", name="tmplProcessId", dataType="Long")
    private Long tmplProcessId;
    public void setTmplProcessId(Long tmplProcessId) { this.tmplProcessId = tmplProcessId; } 
    public Long getTmplProcessId() { return this.tmplProcessId; } 

    /** 抄送人ID mbr_user.id */
    @ApiModelProperty(value="抄送人ID", name="personId", dataType="Long")
    private Long personId;
    public void setPersonId(Long personId) { this.personId = personId; } 
    public Long getPersonId() { return this.personId; } 

    /** 抄送人 mbr_user.username */
    @ApiModelProperty(value="抄送人", name="personName", dataType="String", example="admin")
    private String personName;
    public void setPersonName(String personName) { this.personName = personName; } 
    public String getPersonName() { return this.personName; } 

    @ApiModelProperty(value="创建日期", name="createdTime", dataType="java.util.Date")
    private java.util.Date createdTimeFrom;
    public void setCreatedTimeFrom(java.util.Date createdTimeFrom) { this.createdTimeFrom = createdTimeFrom; } 
    public java.util.Date getCreatedTimeFrom() { return this.createdTimeFrom; } 
    private java.util.Date createdTimeTo;
    public void setCreatedTimeTo(java.util.Date createdTimeTo) { this.createdTimeTo = createdTimeTo; } 
    public java.util.Date getCreatedTimeTo() { return this.createdTimeTo; } 

    @ApiModelProperty(value="更新日期", name="updatedTime", dataType="java.util.Date")
    private java.util.Date updatedTimeFrom;
    public void setUpdatedTimeFrom(java.util.Date updatedTimeFrom) { this.updatedTimeFrom = updatedTimeFrom; } 
    public java.util.Date getUpdatedTimeFrom() { return this.updatedTimeFrom; } 
    private java.util.Date updatedTimeTo;
    public void setUpdatedTimeTo(java.util.Date updatedTimeTo) { this.updatedTimeTo = updatedTimeTo; } 
    public java.util.Date getUpdatedTimeTo() { return this.updatedTimeTo; } 

}
