package com.cnuip.patent.exception;


import com.cnuip.patent.exception.enums.ResultEnum;

/**
 * Create By Crixalis:
 * 2017年5月18日 下午2:52:09
 */
public class FavoriteException extends Exception {


    private ResultEnum resultEnum;


    public FavoriteException(ResultEnum resultEnum) {
        super();
        this.resultEnum = resultEnum;
    }

    public FavoriteException() {
        super();
    }

    public FavoriteException(ResultEnum resultEnum, Throwable throwable) {
        super(throwable);
        this.resultEnum = resultEnum;
    }

    public FavoriteException(String message) {
        super(message);
    }

    public FavoriteException(Throwable cause) {
        super(cause);
    }

    public FavoriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public FavoriteException(String message, Throwable cause, boolean enableSuppression,
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
