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
@ApiModel(value = "操作日志查询实体", description = "操作日志查询实体")
public class UserLogParam extends QueryParam {

    /** mbr_user.id */
    @ApiModelProperty(value="", name="userId", dataType="Long")
    private Long userId;
    public void setUserId(Long userId) { this.userId = userId; } 
    public Long getUserId() { return this.userId; } 

    /** 用户名 mbr_user.username */
    @ApiModelProperty(value="用户名", name="username", dataType="String", example="admin")
    private String username;
    public void setUsername(String username) { this.username = username; } 
    public String getUsername() { return this.username; } 

    /** 内容 */
    @ApiModelProperty(value="内容", name="content", dataType="String", example="操作内容")
    private String content;
    public void setContent(String content) { this.content = content; } 
    public String getContent() { return this.content; } 

    /** 操作时间 */
    @ApiModelProperty(value="创建日期", name="createdTime", dataType="java.util.Date")
    private java.util.Date createdTimeFrom;
    public void setCreatedTimeFrom(java.util.Date createdTimeFrom) { this.createdTimeFrom = createdTimeFrom; } 
    public java.util.Date getCreatedTimeFrom() { return this.createdTimeFrom; } 
    private java.util.Date createdTimeTo;
    public void setCreatedTimeTo(java.util.Date createdTimeTo) { this.createdTimeTo = createdTimeTo; } 
    public java.util.Date getCreatedTimeTo() { return this.createdTimeTo; } 

}
