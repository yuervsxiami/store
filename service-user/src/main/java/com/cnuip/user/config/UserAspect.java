package com.cnuip.user.config;

import com.cnuip.base.domain.user.User;
import com.cnuip.gaea.service.web.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.cnuip.user.enums.RabbitConfigEnums.*;


/**
 * Created by Crixalis on 2018/4/11.
 */

@Component
@Aspect
public class UserAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Around("@annotation(com.cnuip.user.annotation.DealUser)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        try {
            User user = ((ApiResponse<User>) proceed).getResult();
            User newUser = new User();
            BeanUtils.copyProperties(user,newUser);
            amqpTemplate.convertAndSend(STORE_USER_EXCHANGE.getName(), STORE_USER.getName(),new ObjectMapper().writeValueAsString(newUser));

        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return proceed;
    }
}
