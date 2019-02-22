package com.cnuip.colligate.exception.enums;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public enum ClientEnum {

    PROCESS_ERROR(40001,"新增提案失败"),
    PROCESSTASKUSER_ERROR(40002,"提案环节审核人接口调用失败"),
    USER_ERROR(40003,"用户接口调用失败"),
    ORGANIZATION_ERROR(40004,"组织接口调用失败"),
    PATENT_ERROR(40005,"专利管理接口调用失败"),
    PUSH_ERROR(40006,"消息接口调用失败"),
    PROCESSTASK_ERROR(40007,"提案环节接口调用失败"),
    PROCESS_NO_EXIST(40008,"提案编号已被占用"),
    USER_NO_EXIST(40009,"未找到用户信息"),
    AUTHORIZE_ERROR(40010,"委托接口调用失败"),
    ORGANIZATION_NO_EXIST(40011,"未找到组织信息"),
    RESULT_ERROR(40012,"调用新增科技成果接口失败"),

    CNUIP_CLIENT_SHOP_ERROR(50001,"调用cnuip店铺咨询失败"),
    CNUIP_CLIENT_GOODS_ERROR(50002,"调用cnuip商品咨询失败"),
    CNUIP_CLIENT_SEND_AUTHORIZE_ERROR(50003,"同步委托到cnuip失败!"),
    CNUIP_CLIENT_SHOP_UPDATE_ERROR(50004,"调用cnuip店铺修改失败"),
    TIIKONG_CONNECTION_ERROR(50005,"调用天弓接口失败"),
    NAME_NULL_ERROR(50006,"方法名不能为空"),
    PMES_CONNECTION_ERROR(50007,"调用pems专家报告接口失败"),


    ORGANIZATION_ID_CAN_NOT_BE_NULL(60001,"组织id不能为空"),
    SERVICE_ERROR(60002,"服务调用失败"),
    PATENTRESULT_NO_EXIST(60003,"成果编号已存在"),
    ;

    private int code;

    private String msg;

    private ClientEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}