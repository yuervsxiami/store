package com.cnuip.console.aspect;

import com.cnuip.base.domain.console.Splash;
import com.cnuip.console.jedis.JedisClient;
import com.cnuip.console.repository.SplashMapper;
import com.cnuip.console.repository.base.SplashBaseMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;


@Component
@Aspect
public class SplashAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SplashBaseMapper splashBaseMapper;
    @Autowired
    private SplashMapper splashMapper;
    @Autowired
    private JedisClient jedisClient;

    @Pointcut("execution(* com.cnuip.console.service.SplashService.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object sendState(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed;

        proceed = joinPoint.proceed();
        try {
            String name = joinPoint.getSignature().getName();
            if (name.startsWith("insert") || name.startsWith("update") || name.startsWith("delete")) {
                logger.info("---AOP AFTER--------从数据库中找splashCurrent缓存------------------");
                Splash splash =  splashMapper.currentSplash(Calendar.getInstance().getTime());
                logger.info("AOP AFTER splashCurrent "+splash);
                if(splash!=null){
                    //计算缓存过期时间
                    long expireSecond = (splash.getShowEndTime().getTime() - Calendar.getInstance().getTime().getTime())/1000;
                    logger.info("---AOP AFTER--------缓存过期时间"+expireSecond+"--------");
                    jedisClient.del("store2.console.splashCurrent");
                    jedisClient.setExpire("store2.console.splashCurrent",splash,expireSecond);
                }else{
                    logger.info("-----------从缓存清除splashCurrent缓存----------");
                    jedisClient.del("store2.console.splashCurrent");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return proceed;
    }
}
