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
@ApiModel(value = "用户验证码查询实体", description = "用户验证码查询实体")
public class VerifyCodeParam extends QueryParam {

    /** 动作 [ LOGIN.登录 REGISTER.注册 ] */
    /** UserActionEnum */
    @ApiModelProperty(value="动作 [ LOGIN.登录 REGISTER.注册 ]", name="action", dataType="String")
    private String action;
    public void setAction(String action) { this.action = action; } 
    public String getAction() { return this.action; } 

    /** 用户名(手机号码) */
    @ApiModelProperty(value="用户名(手机号码)", name="username", dataType="String")
    private String username;
    public void setUsername(String username) { this.username = username; } 
    public String getUsername() { return this.username; } 

    /** 验证码 */
    @ApiModelProperty(value="验证码", name="code", dataType="String")
    private String code;
    public void setCode(String code) { this.code = code; } 
    public String getCode() { return this.code; } 

    /** 时间 */
    @ApiModelProperty(value="创建日期", name="createdTime", dataType="java.util.Date")
    private java.util.Date createdTimeFrom;
    public void setCreatedTimeFrom(java.util.Date createdTimeFrom) { this.createdTimeFrom = createdTimeFrom; } 
    public java.util.Date getCreatedTimeFrom() { return this.createdTimeFrom; } 
    private java.util.Date createdTimeTo;
    public void setCreatedTimeTo(java.util.Date createdTimeTo) { this.createdTimeTo = createdTimeTo; } 
    public java.util.Date getCreatedTimeTo() { return this.createdTimeTo; } 

}
