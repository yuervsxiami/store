package com.cnuip.registry;

import com.cnuip.gaea.service.GaeaBootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

    public static void main(String[] args) {
        GaeaBootstrap.run(ServiceRegistryApplication.class, args);
    }

}

