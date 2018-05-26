package com.siva.poc.hazelcast.apps.consumer.telemetry;

import java.util.Set;

public class TelemetryDataMessage {

    private final String resourceId;
    private final Object data;
    private final Set<String> interestedDashboardIds;

    public TelemetryDataMessage(String resourceId, Object data, Set<String> interestedDashboardIds) {
        this.resourceId = resourceId;
        this.data = data;
        this.interestedDashboardIds = interestedDashboardIds;
    }

    public String getResourceId() {
        return resourceId;
    }

    public Object getData() {
        return data;
    }

    public Set<String> getInterestedDashboardIds() {
        return interestedDashboardIds;
    }
}
