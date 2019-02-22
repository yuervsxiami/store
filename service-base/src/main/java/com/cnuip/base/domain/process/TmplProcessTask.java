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
@ApiModel(value = "流程环节模版", description = "流程环节模版")
public class TmplProcessTask extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** 序号 */
    @ApiModelProperty(value="序号", name="no", dataType="Integer")
    private Integer no;

    /** 环节名称 */
    @ApiModelProperty(value="环节名称", name="name", dataType="String", example="节点一")
    private String name;

    /** 职权ID sys_powers.id */
    @ApiModelProperty(value="职权ID", name="powersId", dataType="Long")
    private Long powersId;

    /** 职权名称 sys_powers.name */
    @ApiModelProperty(value="职权名称", name="powersName", dataType="String")
    private String powersName;

    /** 流程模版ID pro_tmpl_process.id */
    @ApiModelProperty(value="流程模版ID", name="tmplProcessId", dataType="Long")
    private Long tmplProcessId;

    /** 操作人 mbr_user.id */
    @ApiModelProperty(value="操作人", name="editorId", dataType="Long")
    private Long editorId;

    /** 操作人 mbr_user.username */
    @ApiModelProperty(value="操作人", name="editorName", dataType="String", example="admin")
    private String editorName;

    @ApiModelProperty(value="创建日期", name="createdTime", dataType="java.util.Date")
    private java.util.Date createdTime;

    @ApiModelProperty(value="更新日期", name="updatedTime", dataType="java.util.Date")
    private java.util.Date updatedTime;


    @Override
    public String checkValue(){

        if (this.getNo() == null){
            return "TMPLPROCESSTASK_NO_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getName())){
            return "TMPLPROCESSTASK_NAME_CANNOT_NULL";
        }
        if (this.getName() == null){
            return "TMPLPROCESSTASK_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getName()) > 255){
            return "TMPLPROCESSTASK_NAME_MAX_LENGTH_ERROR";
        }
        if (this.getPowersId() == null){
            return "TMPLPROCESSTASK_POWERS_ID_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getPowersName())){
            return "TMPLPROCESSTASK_POWERS_NAME_CANNOT_NULL";
        }
        if (this.getPowersName() == null){
            return "TMPLPROCESSTASK_POWERS_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getPowersName()) > 255){
            return "TMPLPROCESSTASK_POWERS_NAME_MAX_LENGTH_ERROR";
        }
        if (this.getTmplProcessId() == null){
            return "TMPLPROCESSTASK_TMPL_PROCESS_ID_CANNOT_NULL";
        }

        return null;
    }

    @Override
    public TmplProcessTask setDefaultValue(){

        if (this.getNo() == null){
            this.setNo(1);
        }

        return this;
    }
}
