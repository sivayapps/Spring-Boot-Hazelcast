package com.siva.poc.hazelcast.apps.consumer.telemetry;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;

@Service
public class TelemetryDataConsumer {

    @Autowired
    private HazelcastInstance instance;

    private ApplicationContext applicationContext;

    private Map<String, Set<String>> telemetryFilters;
    private Map<String, Object> telemetryCache;

    @PostConstruct
    private void init() {
        this.telemetryFilters = instance.getMap("telemetryFilters");
        this.telemetryCache = instance.getMap("temetryCache");
    }

    public void onMessage(String key, byte[] data) {
        String resourceId = key.split(":")[1];
        if (telemetryFilters.containsKey(resourceId)) {
            Set<String> dashboards = telemetryFilters.get(resourceId);
            Object dataObj = deserializeMessage(key, data);

            this.telemetryCache.put(resourceId, new TelemetryDataMessage(resourceId, dataObj, dashboards));
            //Publish data to
//            applicationContext.publishEvent(new TelemetryDataMessage(resourceId, dataObj, dashboards));
        }
        //discard message

    }

    private Object deserializeMessage(String key, byte[] data) {
        return null;
    }
}
