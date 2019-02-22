package com.cnuip.base.domain.enums;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public enum ProcessTypeEnum {

    NORMAL("普通"),
    AND("会签"),
    OR("或签 ");

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private ProcessTypeEnum(String name) {
        this.name = name;
    }

}