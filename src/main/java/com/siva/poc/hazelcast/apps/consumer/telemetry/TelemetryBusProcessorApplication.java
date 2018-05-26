package com.siva.poc.hazelcast.apps.consumer.telemetry;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class TelemetryBusProcessorApplication {

    public static void main(String[] args) {
        configureApplication(new SpringApplicationBuilder()).run(args);
    }

    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder
                .sources(TelemetryBusProcessorApplication.class)
                .properties("spring.config.location:classpath:/consumer/telemetry/");//directory path should end with slash '/'
    }

}
