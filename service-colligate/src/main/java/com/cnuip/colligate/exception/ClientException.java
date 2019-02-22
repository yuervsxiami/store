package com.cnuip.colligate.exception;


import com.cnuip.colligate.exception.enums.ClientEnum;

/**
 * Create By Crixalis:
 * 2017年5月18日 下午2:52:09
 */
public class ClientException extends Exception {


    private ClientEnum clientEnum;

    private Integer code;

    public ClientException(ClientEnum clientEnum) {
        super();
        this.clientEnum = clientEnum;
    }

    public ClientException(ClientEnum exceptionEnum,Throwable throwable) {
        super(throwable);
        this.clientEnum = exceptionEnum;
    }

    public ClientException() {
        super();
    }

    public ClientException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public ClientException(String message) {
        super(message);
        this.code = -100;
    }

    public ClientException(Throwable cause) {
        super(cause);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientException(String message, Throwable cause, boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ClientEnum getProcessEnum() {
        return clientEnum;
    }

    public void setProcessEnum(ClientEnum clientEnum) {
        this.clientEnum = clientEnum;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
