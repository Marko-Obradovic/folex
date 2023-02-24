package com.folex.workflow.listener;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * New message from the queue
 */
public class OrderMessage implements Serializable {

    @JsonProperty("source")
    private String source;

    @JsonProperty("description")
    private String description;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OrderMessage{" +
                "source='" + source + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
