package com.cnuip.base.domain.enums;

/**
 * @Author: 陶化伦
 * @Date:   2019/1/22 14:37
 */
public enum AuthorizePatentTradeTypeEnum {

    TRANSFER("转让"),
    LICENSE("许可");

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private AuthorizePatentTradeTypeEnum(String name) {
        this.name = name;
    }

}