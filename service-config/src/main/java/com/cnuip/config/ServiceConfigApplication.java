package com.cnuip.config;

import com.cnuip.gaea.service.GaeaBootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ServiceConfigApplication {

    public static void main(String[] args) {
        GaeaBootstrap.run(ServiceConfigApplication.class, args);
    }

}

