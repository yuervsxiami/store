package com.cnuip.result.exception;


import com.cnuip.result.exception.enums.ResultEnum;

/**
 * Create By Crixalis:
 * 2017年5月18日 下午2:52:09
 */
public class ResultException extends Exception {


    private ResultEnum resultEnum;


    public ResultException(ResultEnum resultEnum) {
        super();
        this.resultEnum = resultEnum;
    }

    public ResultException() {
        super();
    }

    public ResultException(ResultEnum resultEnum, Throwable throwable) {
        super(throwable);
        this.resultEnum = resultEnum;
    }

    public ResultException(String message) {
        super(message);
    }

    public ResultException(Throwable cause) {
        super(cause);
    }

    public ResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultException(String message, Throwable cause, boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }

    public void setResultEnum(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }
}
