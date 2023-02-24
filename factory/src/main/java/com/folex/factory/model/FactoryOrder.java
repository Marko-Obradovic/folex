package com.folex.factory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.UUID;

public class FactoryOrder {

    private final UUID id;

    private final FactoryOutcome outcome;

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

    @JsonIgnore
    public LocalDateTime getCompleteTime() {
        return completeTime;
    }

    public FactoryOutcome getStatus() {
        if(completeTime.isAfter(LocalDateTime.now())) {
            return outcome;
        }

        return FactoryOutcome.IN_PROGRESS;
    }
}
