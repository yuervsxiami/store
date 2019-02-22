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
@ApiModel(value = "流程环节模版查询实体", description = "流程环节模版查询实体")
public class TmplProcessTaskParam extends QueryParam {

    /** 序号 */
    @ApiModelProperty(value="序号", name="no", dataType="Integer")
    private Integer noFrom;
    public void setNoFrom(Integer noFrom) { this.noFrom = noFrom; } 
    public Integer getNoFrom() { return this.noFrom; } 
    private Integer noTo;
    public void setNoTo(Integer noTo) { this.noTo = noTo; } 
    public Integer getNoTo() { return this.noTo; } 

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

    /** 流程模版ID pro_tmpl_process.id */
    @ApiModelProperty(value="流程模版ID", name="tmplProcessId", dataType="Long")
    private Long tmplProcessId;
    public void setTmplProcessId(Long tmplProcessId) { this.tmplProcessId = tmplProcessId; } 
    public Long getTmplProcessId() { return this.tmplProcessId; } 

    /** 操作人 mbr_user.id */
    @ApiModelProperty(value="操作人", name="editorId", dataType="Long")
    private Long editorId;
    public void setEditorId(Long editorId) { this.editorId = editorId; } 
    public Long getEditorId() { return this.editorId; } 

    /** 操作人 mbr_user.username */
    @ApiModelProperty(value="操作人", name="editorName", dataType="String", example="admin")
    private String editorName;
    public void setEditorName(String editorName) { this.editorName = editorName; } 
    public String getEditorName() { return this.editorName; } 

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
