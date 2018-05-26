package com.siva.poc.hazelcast.apps.web;

import com.siva.poc.hazelcast.rest.CityController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackageClasses = CityController.class)
@EnableCaching
public class Application {

    public static void main(String[] args) {
        configureApplication(new SpringApplicationBuilder()).run(args);
    }

    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder
                .sources(Application.class)
                .properties("spring.config.location:classpath:/app/");
    }

}
