package com.cnuip.base.utils;

import com.cnuip.gaea.service.web.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public class ClientServiceUtils<R, T> {
    final Logger logger = LoggerFactory.getLogger(getClass());

    public ClientServiceUtils() {
    }

    public ApiResponse<R> exec(T object, String name, Object... args) {
        Method method = null;
        Method[] methods = object.getClass().getMethods();
        for (Method m : methods) {
            if (m.getName().equals(name)) {
                method = m;
                break;
            }
        }
        ApiResponse<R> res = new ApiResponse<R>();
        try {
            return (ApiResponse<R>) method.invoke(object, args);
        } catch (Exception e) {
            res.setCode(-1);
            res.setMessage(e.getMessage());
            res.setResult(null);
            logger.error(object.getClass() + "服务调用失败，方法名:" + name + ";参数:" + args, e);
            return res;
        }
    }
}
