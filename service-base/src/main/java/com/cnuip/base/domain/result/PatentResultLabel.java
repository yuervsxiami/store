package com.cnuip.base.domain.result;

import com.cnuip.base.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
@Data
@ApiModel(value = "专利成果对应标签表", description = "专利成果对应标签表")
public class PatentResultLabel extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** rlt_patent_result.id */
    @ApiModelProperty(value="", name="patentResultId", dataType="Long")
    private Long patentResultId;

    /** rlt_label.id */
    @ApiModelProperty(value="", name="labelId", dataType="Long")
    private Long labelId;

    /** rlt_label_value.id */
    @ApiModelProperty(value="", name="labelValueId", dataType="Long")
    private Long labelValueId;


    @Override
    public String checkValue(){


        return null;
    }

    @Override
    public PatentResultLabel setDefaultValue(){


        return this;
    }
}
