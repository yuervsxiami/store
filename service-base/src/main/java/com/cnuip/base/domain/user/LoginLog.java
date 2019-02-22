package com.cnuip.base.domain.user;

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
@ApiModel(value = "登录日志", description = "登录日志")
public class LoginLog extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** mbr_user.id */
    @ApiModelProperty(value="", name="userId", dataType="Long")
    private Long userId;

    /** 用户名 mbr_user.username */
    @ApiModelProperty(value="用户名", name="username", dataType="String", example="admin")
    private String username;

    /** IP */
    @ApiModelProperty(value="IP", name="ip", dataType="String", example="'127.0.0.1'")
    private String ip;

    /** 登录时间 */
    @ApiModelProperty(value="创建日期", name="createdTime", dataType="java.util.Date")
    private java.util.Date createdTime;


    @Override
    public String checkValue(){

        if (StringUtils.isNullOrEmpty(this.getUsername())){
            return "LOGINLOG_USER_NAME_CANNOT_NULL";
        }
        if (this.getUsername() == null){
            return "LOGINLOG_USER_NAME_CANNOT_NULL";
        }
        if (StringUtils.stringCount(this.getUsername()) > 255){
            return "LOGINLOG_USER_NAME_MAX_LENGTH_ERROR";
        }

        return null;
    }

    @Override
    public LoginLog setDefaultValue(){

        if (StringUtils.isNullOrEmpty(this.getIp())){
            this.setIp("127.0.0.1");
        }

        return this;
    }
}
