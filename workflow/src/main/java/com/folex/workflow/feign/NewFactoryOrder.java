package com.folex.workflow.feign;

import java.util.UUID;

public class NewFactoryOrder {

    private final UUID id;

    public NewFactoryOrder(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
