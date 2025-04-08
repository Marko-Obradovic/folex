package com.folex.orderservice.watch;

import com.folex.orderservice.model.WatchOrder;

import java.util.Optional;
import java.util.UUID;

public interface OrderManager {

    /**
     * Save data for an order
     * @param order order data
     * @return create a new order
     */
    WatchOrder saveOrder(WatchOrder order);


    /**
     * Get an order by id
     * @param id
     * @return order if found
     */
    Optional<WatchOrder> getOrder(UUID id);

}
