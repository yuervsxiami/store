package com.cnuip.user.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.cnuip.user.config.AliProperties;
import com.cnuip.user.service.AliSmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by wangzhibin on 2018/4/16.
 */
@Service
public class AliSmsServiceImpl implements AliSmsService {

    @Autowired
    private AliProperties aliProperties;

    private final Logger logger = LoggerFactory.getLogger(AliSmsServiceImpl.class);

    @Override
    public Boolean sendSms(String phones, String templateCode, String content, String... params) {
        System.setProperty("sun.net.client.defaultConnectTimeout", aliProperties.getConnectTimeout());
        System.setProperty("sun.net.client.defaultReadTimeout", aliProperties.getReadTimeout());
        final String product = "Dysmsapi";
        final String domain = "dysmsapi.aliyuncs.com";
        try {
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliProperties.getAccessKeyId(), aliProperties.getAccessKeySecret());
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            SendSmsRequest request = new SendSmsRequest();
            request.setMethod(MethodType.POST);
            // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
            request.setPhoneNumbers(phones);
            request.setSignName(aliProperties.getSignName());
            request.setTemplateCode(templateCode);
            request.setTemplateParam(String.format(content, params));

            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            logger.info("alisms info: " + sendSmsResponse.getCode());
            return sendSmsResponse.getCode() != null && "OK".equalsIgnoreCase(sendSmsResponse.getCode());
        } catch (Exception ce) {
            logger.error("alisms error", ce);
            return false;
        }
    }
}