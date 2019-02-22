package com.cnuip.base.utils;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User:zhaozhihui
 * Date: 2019/1/17.
 * Time: 15:12
 */
public class UUidUtils {
    public static String getUUId(int type) {
        //int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return type + String.format("%013d", hashCodeV);
    }
}
