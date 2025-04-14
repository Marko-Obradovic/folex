package com.folex.factory.controller;

import com.folex.factory.model.FactoryOrder;
import com.folex.factory.model.NewOrderRequest;
import com.folex.factory.store.FactoryOrderStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class OrderController {

    private final FactoryOrderStore factoryOrderStore;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderController(FactoryOrderStore factoryOrderStore) {
        this.factoryOrderStore = factoryOrderStore;
    }

    @PostMapping("/factory/order")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addOrder(@RequestBody NewOrderRequest newOrderRequest) {
        factoryOrderStore.createOrder(newOrderRequest.getId());
    }

    @GetMapping("/factory/order/{id}")
    public Optional<FactoryOrder> getOrder(@PathVariable("id") String id) {
        logger.info("Getting order with id {}", id);
        Optional<FactoryOrder> factoryOrder = factoryOrderStore.getOrder(UUID.fromString(id));

        factoryOrder.ifPresent(order -> logger.info("Order found: {}", order));

        return factoryOrder;
    }

    /**
     * Fake update method
     *
     * @param id
     * @param factoryOrder
     * @return
     */
    @PutMapping("/factory/order/{id}")
    public Optional<FactoryOrder> updateOrder(@PathVariable("id") String id, @RequestBody FactoryOrder factoryOrder) {
        return getOrder(id);
    }
}
