package com.cnuip.result.exception.enums;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public enum ResultEnum {

    INSERT_PATENTRESULT_ERROR(60001,"新增专利成果失败"),
    UPDATE_PATENTRESULT_ERROR(60002,"修改专利成果失败"),
    PATENTRESULT_EXIST(60003,"专利成果编号已被占用！");

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