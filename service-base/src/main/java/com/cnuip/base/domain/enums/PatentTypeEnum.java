package com.cnuip.base.domain.enums;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public enum PatentTypeEnum {

    INVENTION("发明专利"),
    APPEARANCE("外观专利"),
    UTILITY("实用新型");

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private PatentTypeEnum(String name) {
        this.name = name;
    }

}