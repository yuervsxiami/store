package com.cnuip.gateway;

import com.cnuip.base.ServiceBaseApplication;
import com.cnuip.gaea.service.GaeaBootstrap;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringCloudApplication
@EnableZuulProxy
public class ServiceGatewayApplication {

    public static void main(String[] args) {
        GaeaBootstrap.run(new Class[]{ServiceGatewayApplication.class, ServiceBaseApplication.class}, args);
    }

}

