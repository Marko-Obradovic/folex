package com.folex.workflow.feign;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.UUID;

public interface OrderApi {

    @RequestLine("POST /order")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);


    @RequestLine("PUT /order/{id}")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    void updateOrder(@Param("id") UUID id, UpdateOrderRequest updateOrderRequest);
}
