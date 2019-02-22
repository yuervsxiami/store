package com.cnuip.process.exception;


import com.cnuip.process.exception.enums.ProcessEnum;

/**
 * Create By Crixalis:
 * 2017年5月18日 下午2:52:09
 */
public class ProcessException extends Exception {


    private ProcessEnum processEnum;


    public ProcessException(ProcessEnum processEnum) {
        super();
        this.processEnum = processEnum;
    }

    public ProcessException() {
        super();
    }

    public ProcessException(ProcessEnum processEnum,Throwable throwable) {
        super(throwable);
        this.processEnum = processEnum;
    }

    public ProcessException(String message) {
        super(message);
    }

    public ProcessException(Throwable cause) {
        super(cause);
    }

    public ProcessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessException(String message, Throwable cause, boolean enableSuppression,
                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ProcessEnum getProcessEnum() {
        return processEnum;
    }

    public void setProcessEnum(ProcessEnum processEnum) {
        this.processEnum = processEnum;
    }
}
