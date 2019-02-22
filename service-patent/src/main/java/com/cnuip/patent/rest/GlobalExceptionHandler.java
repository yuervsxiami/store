package com.cnuip.patent.rest;

import com.cnuip.base.web.BaseException;
import com.cnuip.gaea.service.web.ApiResponse;
import com.cnuip.patent.exception.FavoriteException;
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
        }else if(e instanceof FavoriteException){
            FavoriteException e1 = (FavoriteException) e;
            response.setCode(e1.getResultEnum().getCode());
            response.setMessage(e1.getResultEnum().getMsg());
            response.setDetailMessage(e1.getResultEnum().getMsg());
            logger.error(e1.getResultEnum().getMsg(), e.getCause());
        }else {
            response.setCode(-1);
            response.setMessage("系统内部错误");
            response.setDetailMessage(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return response;
    }
}
