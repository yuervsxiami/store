package com.cnuip.user.enums;

/**
 * Created by wangzhibin on 2018/4/12.
 */
public enum RabbitConfigEnums {

    STORE_USER_EXCHANGE("store_user.exchange"),
    STORE_USER("store_user.message"),
    PROFESSOR_EXCHANGE("professor.exchange"),
    PROFESSOR("professor.message"),
    ;


    private String name;

    RabbitConfigEnums(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}