package com.folex.factory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.UUID;

public class FactoryOrder {

    private final UUID id;

    private FactoryOutcome outcome;

    private final LocalDateTime completeTime;

    public FactoryOrder(UUID id, FactoryOutcome outcome, LocalDateTime completeTime) {
        this.id = id;
        this.outcome = outcome;
        this.completeTime = completeTime;
    }

    public UUID getId() {
        return id;
    }

    @JsonIgnore
    public FactoryOutcome getOutcome() {
        return outcome;
    }


    public LocalDateTime getCompleteTime() {
        return completeTime;
    }

    /**
     * Sets the outcome of the factory order and returns the updated FactoryOrder instance.
     *
     * @param outcome the outcome to set for the factory order. This should be an instance of {@code FactoryOutcome}.
     * @return the updated {@code FactoryOrder} instance.
     */
    public FactoryOrder setOutcome(FactoryOutcome outcome) {
        this.outcome = outcome;
        return this;
    }

    /**
     * Retrieves the current status of the factory order based on its completion time.
     * If the completion time is in the future, returns the current outcome of the order.
     * Otherwise, the status is considered to be {@code FactoryOutcome.IN_PROGRESS}.
     *
     * @return the current status of the factory order, which can be either the current outcome
     *         or {@code FactoryOutcome.IN_PROGRESS}.
     */
    public FactoryOutcome getStatus() {
        return outcome;
    }

    @Override
    public String toString() {
        return "FactoryOrder{" + "id=" + id + ", outcome=" + outcome + ", completeTime=" + completeTime + '}';
    }
}
