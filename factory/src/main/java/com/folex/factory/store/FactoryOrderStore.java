package com.folex.factory.store;

import com.folex.factory.model.FactoryOrder;

import java.util.Optional;
import java.util.UUID;

public interface FactoryOrderStore {

    void createOrder(UUID orderId);

    Optional<FactoryOrder> getOrder(UUID orderId);
}
