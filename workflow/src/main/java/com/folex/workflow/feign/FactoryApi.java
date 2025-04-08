package com.folex.workflow.feign;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface FactoryApi {

    @RequestLine("POST /factory/order")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    void addOrder(NewFactoryOrder newFactoryOrder);

    @RequestLine("GET /factory/order/{id}")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    FactoryOrderStatus getOrder(@Param("id") String id);

}
