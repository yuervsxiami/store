package com.cnuip.user.service;

/**
 * Created by wangzhibin on 2018/4/16.
 */
public interface AliSmsService {
    Boolean sendSms(String phones, String templateCode, String content, String... params);
}
