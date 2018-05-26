package com.siva.poc.hazelcast.apps.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastInstanceFactory;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastProperties;
import org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import java.io.IOException;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {JerseyAutoConfiguration.class})
public class HazelcastApplication {

    public static void main(String[] args) {
        initEnv();
        configureApplication(new SpringApplicationBuilder()).run(args);
    }

    private static void initEnv() {
        System.setProperty("hazelcast.rest.enabled", "true");
//        System.setProperty("hazelcast.diagnostics.enabled", "true");
    }

    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder
                .sources(HazelcastApplication.class)
                .properties("spring.config.location:classpath:/hazelcast/");
    }

    @Bean
    public HazelcastInstance hazelcastInstance(HazelcastProperties properties)
            throws IOException {
        Resource config = properties.resolveConfigLocation();
        if (config != null) {
            return new HazelcastInstanceFactory(config).getHazelcastInstance();
        }
        return Hazelcast.newHazelcastInstance();
    }

}
