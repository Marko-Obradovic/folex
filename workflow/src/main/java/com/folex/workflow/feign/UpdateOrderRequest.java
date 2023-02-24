package com.folex.workflow.feign;

import java.util.UUID;

public class UpdateOrderRequest extends CreateOrderRequest {

    private final UUID id;

    public UpdateOrderRequest(String status, UUID id) {
        super(status, null, null);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
