package com.cnuip.console;

import com.cnuip.base.ServiceBaseApplication;
import com.cnuip.gaea.service.GaeaBootstrap;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@MapperScan(basePackages = {"com.cnuip.console.repository"})
public class ServiceConsoleApplication {

    public static void main(String[] args) {
        GaeaBootstrap.run(new Class[]{ServiceConsoleApplication.class, ServiceBaseApplication.class}, args);
    }

}

