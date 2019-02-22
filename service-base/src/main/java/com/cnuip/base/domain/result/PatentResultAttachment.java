package com.cnuip.base.domain.result;

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
@ApiModel(value = "专利成果附件表", description = "专利成果附件表")
public class PatentResultAttachment extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** rlt_patent_result.id */
    @ApiModelProperty(value="", name="patentResultId", dataType="Long")
    private Long patentResultId;

    /** 附件 url */
    @ApiModelProperty(value="附件", name="url", dataType="String")
    private String url;


    @Override
    public String checkValue(){

        if (StringUtils.isNullOrEmpty(this.getUrl())){
            return "PATENTRESULTATTACHMENT_URL_CANNOT_NULL";
        }
        if (this.getUrl() == null){
            return "PATENTRESULTATTACHMENT_URL_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getUrl()) > 255){
            return "PATENTRESULTATTACHMENT_URL_MAX_LENGTH_ERROR";
        }

        return null;
    }

    @Override
    public PatentResultAttachment setDefaultValue(){


        return this;
    }
}
