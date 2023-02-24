package com.folex.workflow.feign;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateOrderResponse {

    private final UUID id;

    private final String status;

    private final String source;

    private final LocalDateTime orderDate;


    private final LocalDateTime lastUpdate;

    private final String description;

    @JsonCreator
    public CreateOrderResponse(
            @JsonProperty("id") UUID id,
            @JsonProperty("status") String status,
            @JsonProperty("source") String source,
            @JsonProperty("orderDate") LocalDateTime orderDate,
            @JsonProperty("lastUpdate") LocalDateTime lastUpdate,
            @JsonProperty("description") String description) {
        this.id = id;
        this.status = status;
        this.source = source;
        this.orderDate = orderDate;
        this.lastUpdate = lastUpdate;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getSource() {
        return source;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public String getDescription() {
        return description;
    }
}
