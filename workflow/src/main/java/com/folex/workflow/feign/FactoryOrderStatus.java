package com.folex.workflow.feign;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class FactoryOrderStatus {

    private final UUID id;
    private final String status;

    @JsonCreator
    public FactoryOrderStatus(
            @JsonProperty("id") UUID id,
            @JsonProperty("status") String status) {
        this.id = id;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
