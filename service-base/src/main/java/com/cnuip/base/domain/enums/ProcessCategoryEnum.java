package com.cnuip.base.domain.enums;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public enum ProcessCategoryEnum {

    DOMESTIC("国内专利"),
    INTERNATIONAL("国际专利");

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private ProcessCategoryEnum(String name) {
        this.name = name;
    }

}