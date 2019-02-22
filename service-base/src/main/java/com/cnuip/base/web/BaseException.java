package com.cnuip.base.web;


import com.cnuip.base.web.enums.ResponseBaseEnum;

/**
 * Create By Crixalis:
 * 2017年5月18日 下午2:52:09
 */
public class BaseException extends Exception {


    private ResponseBaseEnum responseBaseEnum;


    public BaseException(ResponseBaseEnum responseBaseEnum) {
        super();
        this.responseBaseEnum = responseBaseEnum;
    }

    public BaseException() {
        super();
    }


    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression,
                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ResponseBaseEnum getResponseBaseEnum() {
        return responseBaseEnum;
    }

    public void setResponseBaseEnum(ResponseBaseEnum responseBaseEnum) {
        this.responseBaseEnum = responseBaseEnum;
    }
}
