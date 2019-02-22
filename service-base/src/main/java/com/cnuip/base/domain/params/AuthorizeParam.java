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
@ApiModel(value = "委托合同表查询实体", description = "委托合同表查询实体")
public class AuthorizeParam extends QueryParam {

    /** 组织ID sys_organization.id */
    @ApiModelProperty(value="组织ID", name="organizationId", dataType="Long")
    private Long organizationId;
    public void setOrganizationId(Long organizationId) { this.organizationId = organizationId; } 
    public Long getOrganizationId() { return this.organizationId; } 

    /** 委托编号 */
    @ApiModelProperty(value="委托编号", name="no", dataType="String", example="20181209000001")
    private String no;
    public void setNo(String no) { this.no = no; } 
    public String getNo() { return this.no; } 

    /** 委托期限至 */
    @ApiModelProperty(value="委托期限至", name="endTime", dataType="java.util.Date", example="2020-12-31")
    private java.util.Date endTimeFrom;
    public void setEndTimeFrom(java.util.Date endTimeFrom) { this.endTimeFrom = endTimeFrom; } 
    public java.util.Date getEndTimeFrom() { return this.endTimeFrom; } 
    private java.util.Date endTimeTo;
    public void setEndTimeTo(java.util.Date endTimeTo) { this.endTimeTo = endTimeTo; } 
    public java.util.Date getEndTimeTo() { return this.endTimeTo; } 

    /** 委托状态 [ EXAMINING.待审核 EXAMINED.审核通过 UNEXAMINED.审核不通过 CANCELLED.已取消 ] */
    /** AuthorizeStateEnum */
    @ApiModelProperty(value="委托状态 [ EXAMINING.待审核 EXAMINED.审核通过 UNEXAMINED.审核不通过 CANCELLED.已取消 ]", name="state", dataType="String")
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
