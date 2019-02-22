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
@ApiModel(value = "专利成果表查询实体", description = "专利成果表查询实体")
public class PatentResultParam extends QueryParam {

    /** 组织ID sys_organization.id */
    @ApiModelProperty(value="组织ID", name="organizationId", dataType="Long")
    private Long organizationId;
    public void setOrganizationId(Long organizationId) { this.organizationId = organizationId; } 
    public Long getOrganizationId() { return this.organizationId; } 

    /** 成果编号 */
    @ApiModelProperty(value="成果编号", name="no", dataType="String", example="20181209000001")
    private String no;
    public void setNo(String no) { this.no = no; } 
    public String getNo() { return this.no; } 

    /** 标题 */
    @ApiModelProperty(value="标题", name="title", dataType="String")
    private String title;
    public void setTitle(String title) { this.title = title; } 
    public String getTitle() { return this.title; } 

    /** 简介 */
    @ApiModelProperty(value="简介", name="introduction", dataType="String")
    private String introduction;
    public void setIntroduction(String introduction) { this.introduction = introduction; } 
    public String getIntroduction() { return this.introduction; } 

    /** 内容 */
    @ApiModelProperty(value="内容", name="content", dataType="String")
    private String content;
    public void setContent(String content) { this.content = content; } 
    public String getContent() { return this.content; } 

    /** 专利情况 */
    @ApiModelProperty(value="专利情况", name="patentContent", dataType="String")
    private String patentContent;
    public void setPatentContent(String patentContent) { this.patentContent = patentContent; } 
    public String getPatentContent() { return this.patentContent; } 

    /** 预览图 */
    @ApiModelProperty(value="预览图", name="imageUrl", dataType="String")
    private String imageUrl;
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; } 
    public String getImageUrl() { return this.imageUrl; } 

    /** 成熟度 [ SAMPLE.已有样品 SMALL_TEST.通过小试 PILOT_TEST.通过中试 BATCH_PRODUCTION.可以量产 ] */
    /** PatentResultMaturityEnum */
    @ApiModelProperty(value="成熟度 [ SAMPLE.已有样品 SMALL_TEST.通过小试 PILOT_TEST.通过中试 BATCH_PRODUCTION.可以量产 ]", name="maturity", dataType="String")
    private String maturity;
    public void setMaturity(String maturity) { this.maturity = maturity; } 
    public String getMaturity() { return this.maturity; } 

    /** 项目组ID sys_team.id */
    @ApiModelProperty(value="项目组ID", name="teamId", dataType="Long")
    private Long teamId;
    public void setTeamId(Long teamId) { this.teamId = teamId; } 
    public Long getTeamId() { return this.teamId; } 

    /** 项目组 sys_team.name */
    @ApiModelProperty(value="项目组", name="teamName", dataType="String")
    private String teamName;
    public void setTeamName(String teamName) { this.teamName = teamName; } 
    public String getTeamName() { return this.teamName; } 

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
