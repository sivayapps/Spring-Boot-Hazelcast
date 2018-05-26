package com.siva.poc.hazelcast.apps.consumer.telemetry;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class OnDashboardUpdate {

    @Autowired
    private HazelcastInstance instance;

    private Map<String, Set<String>> telemetryFilters;

    @PostConstruct
    private void init() {
        this.telemetryFilters = instance.getMap("telemetryFilters");
    }

    public void onDashboardPublishEvent(DashboardPublishEvent dashboardPublishEvent) {
        Set<String> resources = dashboardPublishEvent.getResourceIds();
        String dashboardId = dashboardPublishEvent.getDashboardId();

        for (String resource : resources) {
            if (telemetryFilters.containsKey(resource)) {
                Set<String> dashboards = new HashSet<>(telemetryFilters.get(resource));
                dashboards.add(dashboardId);
                telemetryFilters.put(resource, dashboards);
            } else {
                HashSet<String> set = new HashSet<>();
                set.add(dashboardId);
                telemetryFilters.put(resource, set);
            }
        }

    }

}
