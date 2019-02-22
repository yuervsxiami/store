package com.cnuip.console.rest.exceptions;

public class CustomException extends Exception {
    private ResponseEnum exceptionEnum;

    public CustomException(ResponseEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException(Throwable cause, ResponseEnum exceptionEnum) {
        super(cause);
        this.exceptionEnum = exceptionEnum;
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ResponseEnum getExceptionEnum() {
        return this.exceptionEnum;
    }

    public void setExceptionEnum(ResponseEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }
}