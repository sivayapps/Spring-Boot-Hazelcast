package com.siva.poc.hazelcast.apps.web;

import com.siva.poc.hazelcast.rest.Endpoint;
import com.siva.poc.hazelcast.rest.JerseyRestTestResources;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerConfigLocator;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.Path;
import java.util.Map;

@Component
//@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    @Value("${spring.jersey.application-path:/}")
    private String apiPath;

    @Autowired
    private ApplicationContext applicationContext;

    public JerseyConfig() {
        // Register endpoints, providers, ...
    }

    @PostConstruct
    private void postConstruct() {
        this.registerEndpoints();
        this.initSwagger();
    }

    private void initSwagger() {
        // Available at localhost:port/swagger.json
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);

        BeanConfig config = new BeanConfig();
        config.setConfigId("springboot-jersey-swagger-example");
        config.setTitle("Spring Boot + Jersey + Swagger Example");
        config.setVersion("v2");
        config.setSchemes(new String[] { "http", "https" });
        config.setBasePath(this.apiPath);
        config.setResourcePackage("com.siva.poc.hazelcast.rest");
        config.setPrettyPrint(true);
        config.setScan(true);

        SwaggerConfigLocator.getInstance().putConfig(SwaggerContextService.CONFIG_ID_DEFAULT, config);
    }

    private void registerEndpoints() {
        Map<String, Object> restResources = applicationContext.getBeansWithAnnotation(Path.class);
        if (restResources != null) {
            for (Object resource : restResources.values()) {
                this.register(resource);
            }
        }
//        this.register(JerseyRestTestResources.class);
//        this.register(Endpoint.class);
        // Available at /<Jersey's servlet path>/application.wadl
        this.register(WadlResource.class);
    }
}
