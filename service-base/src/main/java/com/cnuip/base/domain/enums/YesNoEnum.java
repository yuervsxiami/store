package com.cnuip.base.domain.enums;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public enum YesNoEnum {

    YES("是"),
    NO("否");

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private YesNoEnum(String name) {
        this.name = name;
    }

}