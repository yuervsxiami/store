package com.cnuip.base.domain.enums;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public enum ProjectSourceEnum {

    SELF_MADE("自拟课题"),
    PROJECT("计划项目"),
    HORIZONTAL("横向课题"),
    OTHER("其它");

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private ProjectSourceEnum(String name) {
        this.name = name;
    }

}