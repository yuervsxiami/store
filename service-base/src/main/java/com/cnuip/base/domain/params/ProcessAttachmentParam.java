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
@ApiModel(value = "提案附件表查询实体", description = "提案附件表查询实体")
public class ProcessAttachmentParam extends QueryParam {

    /** 提案ID pro_process.id */
    @ApiModelProperty(value="提案ID", name="processId", dataType="Long")
    private Long processId;
    public void setProcessId(Long processId) { this.processId = processId; } 
    public Long getProcessId() { return this.processId; } 

    /** 附件url */
    @ApiModelProperty(value="附件", name="url", dataType="String")
    private String url;
    public void setUrl(String url) { this.url = url; } 
    public String getUrl() { return this.url; } 

    @ApiModelProperty(value="创建日期", name="createdTime", dataType="java.util.Date")
    private java.util.Date createdTimeFrom;
    public void setCreatedTimeFrom(java.util.Date createdTimeFrom) { this.createdTimeFrom = createdTimeFrom; } 
    public java.util.Date getCreatedTimeFrom() { return this.createdTimeFrom; } 
    private java.util.Date createdTimeTo;
    public void setCreatedTimeTo(java.util.Date createdTimeTo) { this.createdTimeTo = createdTimeTo; } 
    public java.util.Date getCreatedTimeTo() { return this.createdTimeTo; } 

    @ApiModelProperty(value="更新日期", name="updatedTime", dataType="java.util.Date")
    private java.util.Date updatedTimeFrom;
    public void setUpdatedTimeFrom(java.util.Date updatedTimeFrom) { this.updatedTimeFrom = updatedTimeFrom; } 
    public java.util.Date getUpdatedTimeFrom() { return this.updatedTimeFrom; } 
    private java.util.Date updatedTimeTo;
    public void setUpdatedTimeTo(java.util.Date updatedTimeTo) { this.updatedTimeTo = updatedTimeTo; } 
    public java.util.Date getUpdatedTimeTo() { return this.updatedTimeTo; } 

}
