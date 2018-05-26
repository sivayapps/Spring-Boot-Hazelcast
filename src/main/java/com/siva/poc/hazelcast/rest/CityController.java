package com.siva.poc.hazelcast.rest;

import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static java.lang.System.nanoTime;

@RestController
public class CityController {


    private static final Logger LOGGER = LoggerFactory.getLogger(CityController.class);

    @Bean
    KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Bean
    public IDummyBean dummyBean() {
        return new DummyBean();
    }

    @Autowired
    IDummyBean dummy;

    @Autowired
    HazelcastInstance hazelcastInstance;

    @RequestMapping("/city/get/{id}")
    public String getCity(@PathVariable String id) {
        String logFormat = "%s call took %d millis with result: %s";
        long start1 = nanoTime();
        String city = dummy.getCity(id);
        long end1 = nanoTime();
        LOGGER.info(format(logFormat, "Rest", TimeUnit.NANOSECONDS.toMillis(end1 - start1), city));
        return city;
    }

    @RequestMapping(value = "city/{city}", method = RequestMethod.GET)
    public String setCity(@PathVariable String city) {
        return dummy.setCity(city);
    }
}
