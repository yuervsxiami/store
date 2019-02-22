package com.cnuip.result;

import com.cnuip.base.ServiceBaseApplication;
import com.cnuip.gaea.service.GaeaBootstrap;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@MapperScan(basePackages = {"com.cnuip.result.repository"})
public class ServiceResultApplication {

    public static void main(String[] args) {
        GaeaBootstrap.run(new Class[]{ServiceResultApplication.class, ServiceBaseApplication.class}, args);
    }

}

