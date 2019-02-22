package com.cnuip.base.domain.enums;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public enum ProcessStateEnum {

    EXAMINING("待审核"),
    RUNNING("审核中"),
    FINISHED("已完成"),
    CLOSED("已关闭"),
    UNEXAMINED("审核不通过");

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private ProcessStateEnum(String name) {
        this.name = name;
    }

}