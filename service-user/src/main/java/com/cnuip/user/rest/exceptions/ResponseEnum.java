package com.cnuip.user.rest.exceptions;

public enum ResponseEnum {
    USER_LOGIN_PASSWORD_ERROR(101, "您输入的密码错误"),
    USER_USERNAMEISEXIST(102, "用户名已被占用"),
    USER_LOGIN_STATE_ERROR(109,"登录方式错误"),
    USER_ISNOTEXIST(103, "用户不存在或已禁用"),
    PHONE_ISNOTEXIST(116, "手机号码未注册"),
    PHONE_ISEXIST(118, "手机号码已被注册"),
    VERIFY_CODE_ERROR(117, "动态码错误"),
    VERIFY_CODE_OUTTIME(119, "动态码超时"),
    VERIFY_CODE_NULL(120, "动态码为空"),
    USER_REGIST_ACCOUNT(104, "您未输入用户名"),
    USER_REGIST_PASSWORD(105, "您未输入密码"),
    USER_EMPTYPASSWORD(106, "密码不能为空"),
    USER_WRONGOLDPASSWORD(107, "您输入的旧密码错误"),
    USER_LOGIN_AUTH_ERROR(108, "该用户没有权限"),
    USER_APP_LOGIN_ERROR(109,"APP端admin用户不能登录"),
    ROLE_INSERT_EXIST_ERROR(201, "该角色重名,请联系管理员!"),
    ROLE_NOT_EXIST_ERROR(302, "角色不存在"),
    ROLE_DELETE_ERROR(303, "角色下有用户,无法删除"),
    CNUIP_CONNECT_ERROR(304, "CNUIP接口调用失败"),
    REQUIREMENT_ERROR(305, "要回复的需求不存在"),
    SHOP_ENTER_USERNAME_ERROR(306, "用户名或店铺名已存在"),
    SHOP_NULL_ERROR(307, "找不到对应的专家店铺"),
    ;

    private int code;
    private String msg;

    private ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}