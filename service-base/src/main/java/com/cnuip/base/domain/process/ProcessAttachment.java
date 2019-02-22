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
@ApiModel(value = "提案附件表", description = "提案附件表")
public class ProcessAttachment extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** 提案ID pro_process.id */
    @ApiModelProperty(value="提案ID", name="processId", dataType="Long")
    private Long processId;

    /** 附件url */
    @ApiModelProperty(value="附件", name="url", dataType="String")
    private String url;

    @ApiModelProperty(value="创建日期", name="createdTime", dataType="java.util.Date")
    private java.util.Date createdTime;

    @ApiModelProperty(value="更新日期", name="updatedTime", dataType="java.util.Date")
    private java.util.Date updatedTime;


    @Override
    public String checkValue(){

        if (this.getProcessId() == null){
            return "PROCESSATTACHMENT_PROCESS_ID_CANNOT_NULL";
        }
        if (StringUtils.isNullOrEmpty(this.getUrl())){
            return "PROCESSATTACHMENT_URL_CANNOT_NULL";
        }
        if (this.getUrl() == null){
            return "PROCESSATTACHMENT_URL_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getUrl()) > 255){
            return "PROCESSATTACHMENT_URL_MAX_LENGTH_ERROR";
        }

        return null;
    }

    @Override
    public ProcessAttachment setDefaultValue(){


        return this;
    }
}
