package com.folex.factory.store;

import com.folex.factory.model.FactoryOrder;
import com.folex.factory.model.FactoryOutcome;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InmemoryFactoryOrderStore implements FactoryOrderStore {

    private static final Map<UUID, FactoryOrder> STORE = new ConcurrentHashMap<>();
    private final Random random = new Random();
    public void createOrder(UUID orderId) {
        FactoryOrder factoryOrder = new FactoryOrder(orderId, getOutcome(), getCompleteTime());

        STORE.put(orderId, factoryOrder);
    }

    public Optional<FactoryOrder> getOrder(UUID orderId) {
        return Optional.empty();
    }

    private FactoryOutcome getOutcome() {
        return FactoryOutcome.ERROR;
    }

    private LocalDateTime getCompleteTime() {
        LocalDateTime now = LocalDateTime.now();
        long secondsToAdd = random.nextLong(60, 60*5);

        return now.plusSeconds(secondsToAdd);
    }
}
