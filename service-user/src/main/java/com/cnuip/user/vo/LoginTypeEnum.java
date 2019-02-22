package com.cnuip.user.vo;

import lombok.Getter;

@Getter
public enum LoginTypeEnum {

    MSG("短信登录"),
    PWD("密码登录");

    private String name;
    LoginTypeEnum(String name) {
        this.name = name;
    }
}
