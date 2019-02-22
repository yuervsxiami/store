package com.cnuip.patent;

import com.cnuip.base.ServiceBaseApplication;
import com.cnuip.gaea.service.GaeaBootstrap;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@MapperScan(basePackages = {"com.cnuip.patent.repository"})
public class ServicePatentApplication {

    public static void main(String[] args) {
        GaeaBootstrap.run(new Class[]{ServicePatentApplication.class, ServiceBaseApplication.class}, args);
    }

}

