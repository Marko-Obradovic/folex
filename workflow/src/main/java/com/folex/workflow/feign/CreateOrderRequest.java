package com.folex.workflow.feign;

public class CreateOrderRequest {

    private final String status;

    private final String source;

    private final String description;

    public CreateOrderRequest(String status, String source, String description) {
        this.status = status;
        this.source = source;
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public String getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }
}
