package com.siva.poc.hazelcast.apps.consumer.telemetry;

import java.util.List;
import java.util.Set;

public class DashboardPublishEvent {

    private final String dashboardId;
    private final Set<String> resourceIds;

    public DashboardPublishEvent(String dashboardId, Set<String> resourceIds) {
        this.dashboardId = dashboardId;
        this.resourceIds = resourceIds;
    }

    public String getDashboardId() {
        return dashboardId;
    }

    public Set<String> getResourceIds() {
        return resourceIds;
    }
}
