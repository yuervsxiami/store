package com.cnuip.base.domain.enums;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public enum PatentResultMaturityEnum {

    SAMPLE("已有样品"),
    SMALL_TEST("通过小试"),
    PILOT_TEST("通过中试"),
    BATCH_PRODUCTION("可以量产");

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private PatentResultMaturityEnum(String name) {
        this.name = name;
    }

}