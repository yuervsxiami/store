package com.cnuip.authorize;

import com.cnuip.base.ServiceBaseApplication;
import com.cnuip.gaea.service.GaeaBootstrap;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@MapperScan(basePackages = {"com.cnuip.authorize.repository"})
public class ServiceAuthorizeApplication {

    public static void main(String[] args) {
        GaeaBootstrap.run(new Class[]{ServiceAuthorizeApplication.class, ServiceBaseApplication.class}, args);
    }

}

