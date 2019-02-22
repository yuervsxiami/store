package com.cnuip.process.exception.enums;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public enum ProcessEnum {

    PROCESS_NULL(50001,"提案内容为空"),
    TMPLPROCESS_NULL(50002,"流程模板不存在"),
    TMPLPROCESS_OR_TMPLPROCESSTASK_NULL(50003,"流程模板或流程环节不存在"),
    INSERT_TMPLPROCESSTASK_ERROR(50004,"新增流程环节模板失败"),
    TMPLPROCESSTASK_USED(50005,"流程环节正在使用中，无法删除！"),
    PROCESSTASK_NOT_EXIST(50006,"要审核的环节不存在！"),
    PROCESSTASK_ISFINISH(50007,"要审核的环节已被处理，无法继续审核！"),
    PROCESS_NO_EXIST(50008,"提案编号已被占用");

    private int code;

    private String msg;

    private ProcessEnum(int code, String msg) {
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