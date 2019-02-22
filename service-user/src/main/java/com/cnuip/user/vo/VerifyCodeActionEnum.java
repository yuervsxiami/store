package com.cnuip.user.vo;

import lombok.Getter;

@Getter
public enum VerifyCodeActionEnum {

    LOGIN("登录"),
    REGISTER("注册"),
    FORGET("忘记密码");
    private String name;
    VerifyCodeActionEnum(String name) {
        this.name = name;
    }
}
