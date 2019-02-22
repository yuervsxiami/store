package com.cnuip.base.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public class UrlUtils {

    /**
     * 处理消息头中为null的信息
     *
     * @param servletRequest
     * @param header
     * @return
     */
    public static String headerHandler(HttpServletRequest servletRequest, String header) {
        String str = servletRequest.getHeader(header);
        if (str == null) {
            str = "-1";
        }
        return str;
    }

    /**
     * url解码
     *
     * @param str
     */
    public static String decode(String str) {
        if (StringUtils.isNullOrEmpty(str)) {
            return str;
        }
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }
}
