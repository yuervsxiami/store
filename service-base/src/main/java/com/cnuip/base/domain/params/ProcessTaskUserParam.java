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
@ApiModel(value = "提案环节审核人员对应表查询实体", description = "提案环节审核人员对应表查询实体")
public class ProcessTaskUserParam extends QueryParam {

    /** 提案ID pro_process.id */
    @ApiModelProperty(value="提案ID", name="processId", dataType="Long")
    private Long processId;
    public void setProcessId(Long processId) { this.processId = processId; } 
    public Long getProcessId() { return this.processId; } 

    /** 提案环节ID pro_process_task.id */
    @ApiModelProperty(value="提案环节ID", name="processTaskId", dataType="Long")
    private Long processTaskId;
    public void setProcessTaskId(Long processTaskId) { this.processTaskId = processTaskId; } 
    public Long getProcessTaskId() { return this.processTaskId; } 

    /** 部门ID sys_department.id */
    @ApiModelProperty(value="部门ID", name="departmentId", dataType="Long")
    private Long departmentId;
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; } 
    public Long getDepartmentId() { return this.departmentId; } 

    /** 审核人ID mbr_user.id */
    @ApiModelProperty(value="审核人ID", name="examinId", dataType="Long")
    private Long examinId;
    public void setExaminId(Long examinId) { this.examinId = examinId; } 
    public Long getExaminId() { return this.examinId; } 

    /** 审核人 mbr_user.username */
    @ApiModelProperty(value="审核人", name="examinName", dataType="String", example="admin")
    private String examinName;
    public void setExaminName(String examinName) { this.examinName = examinName; } 
    public String getExaminName() { return this.examinName; } 

    /** 是否审核 [ YES.是 NO.否 NONE.无 ] */
    /** YesNoNoneEnum */
    @ApiModelProperty(value="是否审核 [ YES.是 NO.否 NONE.无 ]", name="isExamined", dataType="String", example="NONE")
    private String isExamined;
    public void setIsExamined(String isExamined) { this.isExamined = isExamined; } 
    public String getIsExamined() { return this.isExamined; } 

    /** 审核意见 */
    @ApiModelProperty(value="审核意见", name="remark", dataType="String")
    private String remark;
    public void setRemark(String remark) { this.remark = remark; } 
    public String getRemark() { return this.remark; } 

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
