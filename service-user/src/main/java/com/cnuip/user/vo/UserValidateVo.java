package com.cnuip.user.vo;

import lombok.Data;

/**
 * Created by Crixalis on 2018/5/2.
 */
@Data
public class UserValidateVo {
    private String realName;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 验证码
     */
    private String code;
    /**
     * 密码
     */
    private String password;
}
