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
@ApiModel(value = "专利成果附件表查询实体", description = "专利成果附件表查询实体")
public class PatentResultAttachmentParam extends QueryParam {

    /** rlt_patent_result.id */
    @ApiModelProperty(value="", name="patentResultId", dataType="Long")
    private Long patentResultId;
    public void setPatentResultId(Long patentResultId) { this.patentResultId = patentResultId; } 
    public Long getPatentResultId() { return this.patentResultId; } 

    /** 附件 url */
    @ApiModelProperty(value="附件", name="url", dataType="String")
    private String url;
    public void setUrl(String url) { this.url = url; } 
    public String getUrl() { return this.url; } 

}
