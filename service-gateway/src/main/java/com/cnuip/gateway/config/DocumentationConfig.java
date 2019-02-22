package com.cnuip.gateway.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        resources.add(swaggerResource("控制台console", "/store2-service-console/v2/api-docs", "2.0"));
        resources.add(swaggerResource("用户user", "/store2-service-user/v2/api-docs", "2.0"));
        resources.add(swaggerResource("提案process", "/store2-service-process/v2/api-docs", "2.0"));
        resources.add(swaggerResource("委托authorize", "/store2-service-authorize/v2/api-docs", "2.0"));
        resources.add(swaggerResource("科技成果result", "/store2-service-result/v2/api-docs", "2.0"));
        resources.add(swaggerResource("专利服务patent", "/store2-service-patent/v2/api-docs", "2.0"));
        resources.add(swaggerResource("综合服务colligate", "/store2-service-colligate/v2/api-docs", "2.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
