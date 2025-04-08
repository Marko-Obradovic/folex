package com.folex.orderservice.controller;

import com.folex.orderservice.model.WatchOrder;
import com.folex.orderservice.watch.OrderManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class OrderController {

    private final OrderManager orderManager;

    public OrderController(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    @PostMapping("/order")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public WatchOrder createOrder(@RequestBody WatchOrder watchOrder) {
        return orderManager.saveOrder(watchOrder);
    }

    @PutMapping("/order/{id}")
    @ResponseBody
    public WatchOrder updateOrder(@PathVariable("id") String id, @RequestBody WatchOrder watchOrder) {
        if(UUID.fromString(id).equals(watchOrder.getId())) {
            throw new RuntimeException("Order ids do not match");
        }

        return orderManager.saveOrder(watchOrder);
    }

    @GetMapping("/order/{id}")
    @ResponseBody
    public Optional<WatchOrder> getOrder(@PathVariable("id") String id) {
        return orderManager.getOrder(UUID.fromString(id));
    }
}
