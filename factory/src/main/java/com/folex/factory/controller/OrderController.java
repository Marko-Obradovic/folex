package com.folex.factory.controller;

import com.folex.factory.model.FactoryOrder;
import com.folex.factory.model.NewOrderRequest;
import com.folex.factory.store.FactoryOrderStore;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class OrderController {

    private final FactoryOrderStore factoryOrderStore;

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
        return factoryOrderStore.getOrder(UUID.fromString(id));
    }
}
