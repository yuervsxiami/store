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
@ApiModel(value = "流程模版对应抄送人", description = "流程模版对应抄送人")
public class TmplProcessPerson extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** 流程模版ID pro_tmpl_process.id */
    @ApiModelProperty(value="流程模版ID", name="tmplProcessId", dataType="Long")
    private Long tmplProcessId;

    /** 抄送人ID mbr_user.id */
    @ApiModelProperty(value="抄送人ID", name="personId", dataType="Long")
    private Long personId;

    /** 抄送人 mbr_user.username */
    @ApiModelProperty(value="抄送人", name="personName", dataType="String", example="admin")
    private String personName;

    @ApiModelProperty(value="创建日期", name="createdTime", dataType="java.util.Date")
    private java.util.Date createdTime;

    @ApiModelProperty(value="更新日期", name="updatedTime", dataType="java.util.Date")
    private java.util.Date updatedTime;


    @Override
    public String checkValue(){

        if (this.getTmplProcessId() == null){
            return "TMPLPROCESSPERSON_TMPL_PROCESS_ID_CANNOT_NULL";
        }
        if (this.getPersonId() == null){
            return "TMPLPROCESSPERSON_PERSON_ID_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getPersonName())){
            return "TMPLPROCESSPERSON_PERSON_NAME_CANNOT_NULL";
        }
        if (this.getPersonName() == null){
            return "TMPLPROCESSPERSON_PERSON_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getPersonName()) > 255){
            return "TMPLPROCESSPERSON_PERSON_NAME_MAX_LENGTH_ERROR";
        }

        return null;
    }

    @Override
    public TmplProcessPerson setDefaultValue(){


        return this;
    }
}
