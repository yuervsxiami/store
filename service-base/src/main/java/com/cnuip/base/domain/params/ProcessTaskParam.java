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
@ApiModel(value = "提案环节表查询实体", description = "提案环节表查询实体")
public class ProcessTaskParam extends QueryParam {

    /** 提案ID pro_process.id */
    @ApiModelProperty(value="提案ID", name="processId", dataType="Long")
    private Long processId;
    public void setProcessId(Long processId) { this.processId = processId; } 
    public Long getProcessId() { return this.processId; } 

    /** 环节模版ID pro_tmpl_process_task.id */
    @ApiModelProperty(value="环节模版ID", name="tmplProcessTaskId", dataType="Long")
    private Long tmplProcessTaskId;
    public void setTmplProcessTaskId(Long tmplProcessTaskId) { this.tmplProcessTaskId = tmplProcessTaskId; } 
    public Long getTmplProcessTaskId() { return this.tmplProcessTaskId; } 

    /** 环节模版序号 pro_tmpl_process_task.no */
    @ApiModelProperty(value="环节模版序号", name="tmplProcessTaskNo", dataType="Long")
    private Long tmplProcessTaskNo;
    public void setTmplProcessTaskNo(Long tmplProcessTaskNo) { this.tmplProcessTaskNo = tmplProcessTaskNo; } 
    public Long getTmplProcessTaskNo() { return this.tmplProcessTaskNo; } 

    /** 环节名称 */
    @ApiModelProperty(value="环节名称", name="name", dataType="String", example="节点一")
    private String name;
    public void setName(String name) { this.name = name; } 
    public String getName() { return this.name; } 

    /** 职权ID sys_powers.id */
    @ApiModelProperty(value="职权ID", name="powersId", dataType="Long")
    private Long powersId;
    public void setPowersId(Long powersId) { this.powersId = powersId; } 
    public Long getPowersId() { return this.powersId; } 

    /** 职权名称 sys_powers.name */
    @ApiModelProperty(value="职权名称", name="powersName", dataType="String")
    private String powersName;
    public void setPowersName(String powersName) { this.powersName = powersName; } 
    public String getPowersName() { return this.powersName; } 

    /** 是否完成 [ YES.是 NO.否 ] */
    /** YesNoEnum */
    @ApiModelProperty(value="是否完成 [ YES.是 NO.否 ]", name="isFinished", dataType="String", example="NO")
    private String isFinished;
    public void setIsFinished(String isFinished) { this.isFinished = isFinished; } 
    public String getIsFinished() { return this.isFinished; } 

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
