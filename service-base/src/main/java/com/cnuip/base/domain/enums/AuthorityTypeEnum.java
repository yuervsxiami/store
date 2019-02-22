package com.cnuip.base.domain.enums;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public enum AuthorityTypeEnum {

    MENU("菜单"),
    BUTTON("按钮"),
    TAB("页夹");

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private AuthorityTypeEnum(String name) {
        this.name = name;
    }

}