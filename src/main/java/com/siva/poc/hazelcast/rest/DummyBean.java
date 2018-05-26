package com.siva.poc.hazelcast.rest;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.concurrent.TimeUnit;

public class DummyBean implements IDummyBean {

    @Cacheable(value = "city", key = "#id")
    public String getCity(String id) {
        System.out.println("DummyBean.getCity() called!");
        try {
            // emulation of slow method
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Ankara";
    }

    @CachePut(value = "city", key = "#city")
    public String setCity(String city) {
        return city;
    }
}