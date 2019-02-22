package com.cnuip.base.domain.process;

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
@ApiModel(value = "流程环节模版对应部门", description = "流程环节模版对应部门")
public class TmplProcessTaskDepartment extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** 部门ID sys_department.id */
    @ApiModelProperty(value="部门ID", name="departmentId", dataType="Long")
    private Long departmentId;

    /** 部门名称 sys_department.name */
    @ApiModelProperty(value="部门名称", name="departmentName", dataType="String")
    private String departmentName;

    /** 环节ID pro_tmpl_process_task.id */
    @ApiModelProperty(value="环节ID", name="tmplProcessTaskId", dataType="Long")
    private Long tmplProcessTaskId;

    @ApiModelProperty(value="创建日期", name="createdTime", dataType="java.util.Date")
    private java.util.Date createdTime;

    @ApiModelProperty(value="更新日期", name="updatedTime", dataType="java.util.Date")
    private java.util.Date updatedTime;


    @Override
    public String checkValue(){

        if (this.getDepartmentId() == null){
            return "TMPLPROCESSTASKDEPARTMENT_DEPARTMENT_ID_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getDepartmentName())){
            return "TMPLPROCESSTASKDEPARTMENT_DEPARTMENT_NAME_CANNOT_NULL";
        }
        if (this.getDepartmentName() == null){
            return "TMPLPROCESSTASKDEPARTMENT_DEPARTMENT_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getDepartmentName()) > 255){
            return "TMPLPROCESSTASKDEPARTMENT_DEPARTMENT_NAME_MAX_LENGTH_ERROR";
        }
        if (this.getTmplProcessTaskId() == null){
            return "TMPLPROCESSTASKDEPARTMENT_TMPL_PROCESS_TASK_ID_CANNOT_NULL";
        }

        return null;
    }

    @Override
    public TmplProcessTaskDepartment setDefaultValue(){


        return this;
    }
}
