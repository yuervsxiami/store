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
@ApiModel(value = "流程环节模版对应部门查询实体", description = "流程环节模版对应部门查询实体")
public class TmplProcessTaskDepartmentParam extends QueryParam {

    /** 部门ID sys_department.id */
    @ApiModelProperty(value="部门ID", name="departmentId", dataType="Long")
    private Long departmentId;
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; } 
    public Long getDepartmentId() { return this.departmentId; } 

    /** 部门名称 sys_department.name */
    @ApiModelProperty(value="部门名称", name="departmentName", dataType="String")
    private String departmentName;
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; } 
    public String getDepartmentName() { return this.departmentName; } 

    /** 环节ID pro_tmpl_process_task.id */
    @ApiModelProperty(value="环节ID", name="tmplProcessTaskId", dataType="Long")
    private Long tmplProcessTaskId;
    public void setTmplProcessTaskId(Long tmplProcessTaskId) { this.tmplProcessTaskId = tmplProcessTaskId; } 
    public Long getTmplProcessTaskId() { return this.tmplProcessTaskId; } 

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
