package com.folex.orderservice.controller;

import com.folex.orderservice.model.WatchOrder;
import com.folex.orderservice.watch.OrderManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class OrderController {

    private final OrderManager orderManager;
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public WatchOrder createOrder(@RequestBody WatchOrder watchOrder) {
        WatchOrder watch = orderManager.saveOrder(watchOrder);

        logger.info("Order created: {}", watch);
        return watch;
    }

    @PutMapping("/order/{id}")
    @ResponseBody
    public WatchOrder updateOrder(@PathVariable("id") String id, @RequestBody WatchOrder watchOrder) {
        try {
            if (UUID.fromString(id)
                .equals(watchOrder.getId())) {
                throw new RuntimeException("Order ids do not match");
            }

            return orderManager.saveOrder(watchOrder);
        }catch (Exception e) {
            return getOrder(id).orElse(null);
        }
    }

    @GetMapping("/order/{id}")
    @ResponseBody
    public Optional<WatchOrder> getOrder(@PathVariable("id") String id) {
        return orderManager.getOrder(UUID.fromString(id));
    }
}
