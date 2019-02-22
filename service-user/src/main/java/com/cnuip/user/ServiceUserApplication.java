package com.cnuip.user;

import com.cnuip.base.ServiceBaseApplication;
import com.cnuip.gaea.service.GaeaBootstrap;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@MapperScan(basePackages = {"com.cnuip.user.repository"})
public class ServiceUserApplication {

    public static void main(String[] args) {
        GaeaBootstrap.run(new Class[]{ServiceUserApplication.class, ServiceBaseApplication.class}, args);
    }

}

