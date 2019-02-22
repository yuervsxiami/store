package com.cnuip.base.domain.enums;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public enum UserActionEnum {

    LOGIN("登录"),
    REGISTER("注册");

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private UserActionEnum(String name) {
        this.name = name;
    }

}