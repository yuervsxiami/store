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
@ApiModel(value = "用户验证码", description = "用户验证码")
public class VerifyCode extends BaseModel {
    
    /** ID系统自动生成 */
    @ApiModelProperty(value="ID系统自动生成", name="id", dataType="Long")
    private Long id;

    /** 动作 [ LOGIN.登录 REGISTER.注册 ] */
    /** UserActionEnum */
    @ApiModelProperty(value="动作 [ LOGIN.登录 REGISTER.注册 ]", name="action", dataType="String")
    private String action;

    /** 用户名(手机号码) */
    @ApiModelProperty(value="用户名(手机号码)", name="username", dataType="String")
    private String username;

    /** 验证码 */
    @ApiModelProperty(value="验证码", name="code", dataType="String")
    private String code;

    /** 时间 */
    @ApiModelProperty(value="创建日期", name="createdTime", dataType="java.util.Date")
    private java.util.Date createdTime;


    @Override
    public String checkValue(){

        if (StringUtils.stringCount(this.getUsername()) > 255){
            return "VERIFYCODE_USERNAME_MAX_LENGTH_ERROR";
        }
        if (StringUtils.stringCount(this.getCode()) > 255){
            return "VERIFYCODE_CODE_MAX_LENGTH_ERROR";
        }

        return null;
    }

    @Override
    public VerifyCode setDefaultValue(){


        return this;
    }
}
