package com.cnuip.patent.exception.enums;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public enum ResultEnum {

    FAVORITE_EXIST(70001,"该专利已被收藏，无须重复收藏");

    private int code;

    private String msg;

    private ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}