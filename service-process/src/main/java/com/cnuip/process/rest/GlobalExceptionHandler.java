package com.cnuip.process.rest;
/**
 * Create By Crixalis:
 * 2017年5月19日 上午10:56:34
 */

import com.cnuip.base.web.BaseException;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.process.exception.ProcessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse cExceptionHandler(Exception e) {
        ApiResponse response = new ApiResponse<>();
        if (e instanceof BaseException) {
            BaseException e1 = (BaseException) e;
            response.setCode(e1.getResponseBaseEnum().getCode());
            response.setMessage(e1.getResponseBaseEnum().getMsg());
            response.setDetailMessage(e1.getResponseBaseEnum().getMsg());
            logger.error(e1.getResponseBaseEnum().getMsg(), e.getCause());
        }else if(e instanceof ProcessException){
            ProcessException e1 = (ProcessException) e;
            response.setCode(e1.getProcessEnum().getCode());
            response.setMessage(e1.getProcessEnum().getMsg());
            response.setDetailMessage(e1.getProcessEnum().getMsg());
            logger.error(e1.getProcessEnum().getMsg(), e.getCause());
        }else {
            response.setCode(-1);
            response.setMessage("系统内部错误");
            response.setDetailMessage(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return response;
    }
}
