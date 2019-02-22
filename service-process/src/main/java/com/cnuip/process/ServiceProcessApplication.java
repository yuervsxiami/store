package com.cnuip.process;

import com.cnuip.base.ServiceBaseApplication;
import com.cnuip.gaea.service.GaeaBootstrap;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@MapperScan(basePackages = {"com.cnuip.process.repository"})
public class ServiceProcessApplication {

    public static void main(String[] args) {
        GaeaBootstrap.run(new Class[]{ServiceProcessApplication.class, ServiceBaseApplication.class}, args);
    }

}

