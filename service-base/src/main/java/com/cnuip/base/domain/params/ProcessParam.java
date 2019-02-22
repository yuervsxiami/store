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
@ApiModel(value = "提案表查询实体", description = "提案表查询实体")
public class ProcessParam extends QueryParam {

    /** 组织ID sys_organization.id */
    @ApiModelProperty(value="组织ID", name="organizationId", dataType="Long")
    private Long organizationId;
    public void setOrganizationId(Long organizationId) { this.organizationId = organizationId; } 
    public Long getOrganizationId() { return this.organizationId; } 

    /** 流程模版ID pro_tmpl_process.id */
    @ApiModelProperty(value="流程模版ID", name="tmplProcessId", dataType="Long")
    private Long tmplProcessId;
    public void setTmplProcessId(Long tmplProcessId) { this.tmplProcessId = tmplProcessId; } 
    public Long getTmplProcessId() { return this.tmplProcessId; } 

    /** 流程类型 [ NORMAL.普通 AND.会签 OR.或签  ] */
    /** ProcessTypeEnum */
    @ApiModelProperty(value="流程类型 [ NORMAL.普通 AND.会签 OR.或签  ]", name="type", dataType="String", example="NORMAL")
    private String type;
    public void setType(String type) { this.type = type; } 
    public String getType() { return this.type; } 

    /** 提案编号 */
    @ApiModelProperty(value="提案编号", name="no", dataType="String", example="20181209000001")
    private String no;
    public void setNo(String no) { this.no = no; } 
    public String getNo() { return this.no; } 

    /** 提案名称 */
    @ApiModelProperty(value="提案名称", name="name", dataType="String", example="动力学院专利申请提案")
    private String name;
    public void setName(String name) { this.name = name; } 
    public String getName() { return this.name; } 

    /** 申请类别 [ DOMESTIC.国内专利 INTERNATIONAL.国际专利 ] */
    /** ProcessCategoryEnum */
    @ApiModelProperty(value="申请类别 [ DOMESTIC.国内专利 INTERNATIONAL.国际专利 ]", name="category", dataType="String", example="DOMESTIC")
    private String category;
    public void setCategory(String category) { this.category = category; } 
    public String getCategory() { return this.category; } 

    /** 专利类型 [ INVENTION.发明专利 APPEARANCE.外观专利 UTILITY.实用新型 ] */
    /** PatentTypeEnum */
    @ApiModelProperty(value="专利类型 [ INVENTION.发明专利 APPEARANCE.外观专利 UTILITY.实用新型 ]", name="patentType", dataType="String", example="INVENTION")
    private String patentType;
    public void setPatentType(String patentType) { this.patentType = patentType; } 
    public String getPatentType() { return this.patentType; } 

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

    /** 项目来源 [ SELF_MADE.自拟课题 PROJECT.计划项目 HORIZONTAL.横向课题 OTHER.其它 ] */
    /** ProjectSourceEnum */
    @ApiModelProperty(value="项目来源 [ SELF_MADE.自拟课题 PROJECT.计划项目 HORIZONTAL.横向课题 OTHER.其它 ]", name="source", dataType="String", example="DOMESTIC")
    private String source;
    public void setSource(String source) { this.source = source; } 
    public String getSource() { return this.source; } 

    /** 关联项目组ID sys_team.id */
    @ApiModelProperty(value="关联项目组ID", name="teamId", dataType="Long")
    private Long teamId;
    public void setTeamId(Long teamId) { this.teamId = teamId; } 
    public Long getTeamId() { return this.teamId; } 

    /** 关联项目组名称 sys_team.name */
    @ApiModelProperty(value="关联项目组名称", name="teamName", dataType="String")
    private String teamName;
    public void setTeamName(String teamName) { this.teamName = teamName; } 
    public String getTeamName() { return this.teamName; } 

    /** 状态 [ EXAMINING.待审核 RUNNING.审核中 FINISHED.已完成 CLOSED.已关闭 UNEXAMINED.审核不通过 ] */
    /** ProcessStateEnum */
    @ApiModelProperty(value="状态 [ EXAMINING.待审核 RUNNING.审核中 FINISHED.已完成 CLOSED.已关闭 UNEXAMINED.审核不通过 ]", name="state", dataType="String", example="EXAMINING")
    private String state;
    public void setState(String state) { this.state = state; } 
    public String getState() { return this.state; } 

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
