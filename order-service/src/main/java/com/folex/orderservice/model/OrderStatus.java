package com.folex.orderservice.model;

/**
 * Current status of the order
 */
public enum OrderStatus {

    RECEIVED,

    BUILDING,

    SHIPPED,

    CANCELLED,

    ERROR
}
