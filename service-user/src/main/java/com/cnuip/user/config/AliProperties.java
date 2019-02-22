package com.cnuip.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by wangzhibin on 2018/4/16.
 */

@ConfigurationProperties(prefix = "ali")
@Data
public class AliProperties {
    private String connectTimeout;
    private String readTimeout;
    private String accessKeyId;
    private String accessKeySecret;
    private String signName;
}