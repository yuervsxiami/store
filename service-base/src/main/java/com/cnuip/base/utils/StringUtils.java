package com.cnuip.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 王志斌
 * @Date:   2018/4/3 15:27
 */
public class StringUtils {

    public static String replaceUsername(String param){
        if (StringUtils.isNullOrEmpty(param)){
            return param;
        }
        if (param.equals("user_name")){
            return "username";
        }
        return param;
    }

    /**
     * 驼峰 TO 下划线
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        int i = 0;
        StringBuilder sb = new StringBuilder(len);
        while (i < len) {
            char c = param.charAt(i);
            String key1 = i + 4 > len ? "" : param.substring(i, i + 4).toLowerCase();
            String key2 = i + 3 > len ? "" : param.substring(i, i + 3).toLowerCase();
            if (key1.equals("desc")) {
                sb.append("DESC");
                i += 4;
            } else if (key2.equals("asc")) {
                sb.append("ASC");
                i += 3;
            } else {
                if (Character.isUpperCase(c)) {
                    sb.append('_');
                    sb.append(Character.toLowerCase(c));
                } else {
                    sb.append(c);
                }
                i++;
            }
        }
        return sb.toString();
    }

    /**
     * 下划线 TO 驼峰
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        param = StringUtils.replaceUsername(param);
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == '_') {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 首字母大写
     * @param param
     * @return
     */
     public static String firstUpper(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        param = param.substring(0, 1).toUpperCase() + param.substring(1);
        return param;
    }

    /**
     * 首字母小写
     * @param param
     * @return
     */
    public static String firstLower(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        param = param.substring(0, 1).toLowerCase() + param.substring(1);
        return param;
    }

    /**
     * 空字符串
     * @param param
     * @return
     */
    public static Boolean isNullOrEmpty(String param) {
        return param==null || param=="" || param.trim()=="";
    }

    /**
     * 是否包含汉字
     * @param str
     * @return
     */
    private static Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
    public static boolean isContainsChinese(String str)
    {
        Matcher matcher = pattern.matcher(str);
        boolean flg = false;
        if (matcher.find())    {
            flg = true;
        }
        return flg;
    }

    /**
     * 获得字符串字节数
     * @param str
     * @return
     */
    public static Integer stringCount(String str) {
        try {
            return str.getBytes("UTF-8").length;
        } catch (Exception e) {
            return 0;
        }
    }
}
