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
@ApiModel(value = "提案环节审核人员对应表", description = "提案环节审核人员对应表")
public class ProcessTaskUser extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** 提案ID pro_process.id */
    @ApiModelProperty(value="提案ID", name="processId", dataType="Long")
    private Long processId;

    /** 提案环节ID pro_process_task.id */
    @ApiModelProperty(value="提案环节ID", name="processTaskId", dataType="Long")
    private Long processTaskId;

    /** 部门ID sys_department.id */
    @ApiModelProperty(value="部门ID", name="departmentId", dataType="Long")
    private Long departmentId;

    /** 审核人ID mbr_user.id */
    @ApiModelProperty(value="审核人ID", name="examinId", dataType="Long")
    private Long examinId;

    /** 审核人 mbr_user.username */
    @ApiModelProperty(value="审核人", name="examinName", dataType="String", example="admin")
    private String examinName;

    /** 是否审核 [ YES.是 NO.否 NONE.无 ] */
    /** YesNoNoneEnum */
    @ApiModelProperty(value="是否审核 [ YES.是 NO.否 NONE.无 ]", name="isExamined", dataType="String", example="NONE")
    private String isExamined;

    /** 审核意见 */
    @ApiModelProperty(value="审核意见", name="remark", dataType="String")
    private String remark;

    @ApiModelProperty(value="创建日期", name="createdTime", dataType="java.util.Date")
    private java.util.Date createdTime;

    @ApiModelProperty(value="更新日期", name="updatedTime", dataType="java.util.Date")
    private java.util.Date updatedTime;


    @Override
    public String checkValue(){

        if (this.getProcessId() == null){
            return "PROCESSTASKUSER_PROCESS_ID_CANNOT_NULL";
        }
        if (this.getProcessTaskId() == null){
            return "PROCESSTASKUSER_PROCESS_TASK_ID_CANNOT_NULL";
        }
        if (this.getDepartmentId() == null){
            return "PROCESSTASKUSER_DEPARTMENT_ID_CANNOT_NULL";
        }
        if (this.getExaminId() == null){
            return "PROCESSTASKUSER_EXAMIN_ID_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getExaminName())){
            return "PROCESSTASKUSER_EXAMIN_NAME_CANNOT_NULL";
        }
        if (this.getExaminName() == null){
            return "PROCESSTASKUSER_EXAMIN_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getExaminName()) > 255){
            return "PROCESSTASKUSER_EXAMIN_NAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getIsExamined())){
            return "PROCESSTASKUSER_IS_EXAMINED_CANNOT_NULL";
        }
        if (this.getIsExamined() == null){
            return "PROCESSTASKUSER_IS_EXAMINED_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getRemark()) > 255){
            return "PROCESSTASKUSER_REMARK_MAX_LENGTH_ERROR";
        }

        return null;
    }

    @Override
    public ProcessTaskUser setDefaultValue(){

        if (StringUtils.isNullOrEmpty(this.getIsExamined())){
            this.setIsExamined("NONE");
        }

        return this;
    }
}
