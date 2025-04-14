package com.folex.factory.store;

import com.folex.factory.model.FactoryOrder;
import com.folex.factory.model.FactoryOutcome;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        if(STORE.containsKey(orderId)) {
            FactoryOrder factoryOrder = STORE.get(orderId);

            if(FactoryOutcome.IN_PROGRESS.equals(factoryOrder.getOutcome()) && LocalDateTime.now().isAfter(factoryOrder.getCompleteTime())) {
                 int randomOutcome = random.nextInt(100-1) + 1;

                 if(randomOutcome > 85) {
                     factoryOrder.setOutcome(FactoryOutcome.ERROR);
                 } else {
                     factoryOrder.setOutcome(FactoryOutcome.SUCCESS);
                 }

                 STORE.put(orderId, factoryOrder);
            }

            return Optional.of(factoryOrder);
        }

        return Optional.empty();
    }

    private FactoryOutcome getOutcome() {
        return FactoryOutcome.IN_PROGRESS;
    }

    /**
     * Generates a future completion time by adding a random number of seconds to the current time.
     * The random number of seconds is between 60 and 300.
     *
     * @return a LocalDateTime representing the completion time.
     */
    private LocalDateTime getCompleteTime() {
        LocalDateTime now = LocalDateTime.now();
        long secondsToAdd = random.nextLong(20, 60);

        return now.plusSeconds(secondsToAdd);
    }
}
