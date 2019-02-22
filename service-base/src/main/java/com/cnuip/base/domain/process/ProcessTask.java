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
@ApiModel(value = "提案环节表", description = "提案环节表")
public class ProcessTask extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** 提案ID pro_process.id */
    @ApiModelProperty(value="提案ID", name="processId", dataType="Long")
    private Long processId;

    /** 环节模版ID pro_tmpl_process_task.id */
    @ApiModelProperty(value="环节模版ID", name="tmplProcessTaskId", dataType="Long")
    private Long tmplProcessTaskId;

    /** 环节模版序号 pro_tmpl_process_task.no */
    @ApiModelProperty(value="环节模版序号", name="tmplProcessTaskNo", dataType="Long")
    private Long tmplProcessTaskNo;

    /** 环节名称 */
    @ApiModelProperty(value="环节名称", name="name", dataType="String", example="节点一")
    private String name;

    /** 职权ID sys_powers.id */
    @ApiModelProperty(value="职权ID", name="powersId", dataType="Long")
    private Long powersId;

    /** 职权名称 sys_powers.name */
    @ApiModelProperty(value="职权名称", name="powersName", dataType="String")
    private String powersName;

    /** 是否完成 [ YES.是 NO.否 ] */
    /** YesNoEnum */
    @ApiModelProperty(value="是否完成 [ YES.是 NO.否 ]", name="isFinished", dataType="String", example="NO")
    private String isFinished;

    @ApiModelProperty(value="创建日期", name="createdTime", dataType="java.util.Date")
    private java.util.Date createdTime;

    @ApiModelProperty(value="更新日期", name="updatedTime", dataType="java.util.Date")
    private java.util.Date updatedTime;


    @Override
    public String checkValue(){

        if (this.getProcessId() == null){
            return "PROCESSTASK_PROCESS_ID_CANNOT_NULL";
        }
        if (this.getTmplProcessTaskId() == null){
            return "PROCESSTASK_TMPL_PROCESS_TASK_ID_CANNOT_NULL";
        }
        if (this.getTmplProcessTaskNo() == null){
            return "PROCESSTASK_TMPL_PROCESS_TASK_NO_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getName())){
            return "PROCESSTASK_NAME_CANNOT_NULL";
        }
        if (this.getName() == null){
            return "PROCESSTASK_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getName()) > 255){
            return "PROCESSTASK_NAME_MAX_LENGTH_ERROR";
        }
        if (this.getPowersId() == null){
            return "PROCESSTASK_POWERS_ID_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getPowersName())){
            return "PROCESSTASK_POWERS_NAME_CANNOT_NULL";
        }
        if (this.getPowersName() == null){
            return "PROCESSTASK_POWERS_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getPowersName()) > 255){
            return "PROCESSTASK_POWERS_NAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.isNullOrEmpty(this.getIsFinished())){
            return "PROCESSTASK_IS_FINISHED_CANNOT_NULL";
        }
        if (this.getIsFinished() == null){
            return "PROCESSTASK_IS_FINISHED_CANNOT_NULL";
        }

        return null;
    }

    @Override
    public ProcessTask setDefaultValue(){

        if (StringUtils.isNullOrEmpty(this.getIsFinished())){
            this.setIsFinished("NO");
        }

        return this;
    }
}
