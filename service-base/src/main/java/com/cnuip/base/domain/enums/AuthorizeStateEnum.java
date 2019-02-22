package com.cnuip.base.domain.enums;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public enum AuthorizeStateEnum {

    EXAMINING("待审核"),
    EXAMINED("审核通过"),
    UNEXAMINED("审核不通过"),
    CANCELLED("已取消");

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private AuthorizeStateEnum(String name) {
        this.name = name;
    }

}