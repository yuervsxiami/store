package com.cnuip.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by wangzhibin on 2018/4/16.
 */
@Configuration
public class AliConfig {
    @Bean
    public AliProperties aliProperties() {
        return new AliProperties();
    }
}
