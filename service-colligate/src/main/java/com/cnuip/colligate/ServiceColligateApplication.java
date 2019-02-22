package com.cnuip.colligate;

import com.cnuip.base.ServiceBaseApplication;
import com.cnuip.gaea.service.GaeaBootstrap;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
public class ServiceColligateApplication {

    public static void main(String[] args) {
        GaeaBootstrap.run(new Class[]{ServiceColligateApplication.class, ServiceBaseApplication.class}, args);
    }

}

