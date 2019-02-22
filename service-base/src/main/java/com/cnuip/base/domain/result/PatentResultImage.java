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
@ApiModel(value = "专利成果展示图表", description = "专利成果展示图表")
public class PatentResultImage extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** rlt_patent_result.id */
    @ApiModelProperty(value="", name="patentResultId", dataType="Long")
    private Long patentResultId;

    /** 专利成果图片 */
    @ApiModelProperty(value="专利成果图片", name="url", dataType="String")
    private String url;

    /** 备注 */
    @ApiModelProperty(value="备注", name="remark", dataType="String")
    private String remark;


    @Override
    public String checkValue(){

        if (StringUtils.isNullOrEmpty(this.getUrl())){
            return "PATENTRESULTIMAGE_URL_CANNOT_NULL";
        }
        if (this.getUrl() == null){
            return "PATENTRESULTIMAGE_URL_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getUrl()) > 255){
            return "PATENTRESULTIMAGE_URL_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getRemark()) > 255){
            return "PATENTRESULTIMAGE_REMARK_MAX_LENGTH_ERROR";
        }

        return null;
    }

    @Override
    public PatentResultImage setDefaultValue(){


        return this;
    }
}
