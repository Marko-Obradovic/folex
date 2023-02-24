package com.folex.orderservice.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data object to describe a watch order
 */
public class WatchOrder {

    private final UUID id;

    private final OrderStatus status;

    private final String source;

    private final LocalDateTime orderDate;


    private final LocalDateTime lastUpdate;

    private final String description;

    public WatchOrder(UUID id, OrderStatus status, String source, LocalDateTime orderDate, LocalDateTime lastUpdate, String description) {
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

    public OrderStatus getStatus() {
        return status;
    }

    public String getSource() {
        return source;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
}
