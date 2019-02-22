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
@ApiModel(value = "专利成果对应标签表查询实体", description = "专利成果对应标签表查询实体")
public class PatentResultLabelParam extends QueryParam {

    /** rlt_patent_result.id */
    @ApiModelProperty(value="", name="patentResultId", dataType="Long")
    private Long patentResultId;
    public void setPatentResultId(Long patentResultId) { this.patentResultId = patentResultId; } 
    public Long getPatentResultId() { return this.patentResultId; } 

    /** rlt_label.id */
    @ApiModelProperty(value="", name="labelId", dataType="Long")
    private Long labelId;
    public void setLabelId(Long labelId) { this.labelId = labelId; } 
    public Long getLabelId() { return this.labelId; } 

    /** rlt_label_value.id */
    @ApiModelProperty(value="", name="labelValueId", dataType="Long")
    private Long labelValueId;
    public void setLabelValueId(Long labelValueId) { this.labelValueId = labelValueId; } 
    public Long getLabelValueId() { return this.labelValueId; } 

}
