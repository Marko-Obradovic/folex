package com.folex.orderservice.watch;

import com.folex.orderservice.model.WatchOrder;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class JdbiOrderManager implements OrderManager {

    private final Jdbi jdbi;

    public JdbiOrderManager(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    /**
     * Save data for an order
     *
     * @param order order data
     * @return create a new order
     */
    @Override
    public WatchOrder saveOrder(WatchOrder order) {
        final UUID sourceId = jdbi.withExtension(OrderDao.class, h -> h.getSourceId(order.getSource()));

        if(order.getId() == null) {
            UUID id = UUID.randomUUID();
            jdbi.useExtension(OrderDao.class, h -> h.insertOrder(order, sourceId, id));

            return getOrder(id).orElseThrow(RuntimeException::new);
        }

        jdbi.useExtension(OrderDao.class, h -> h.updateOrder(order));

        return jdbi.withExtension(OrderDao.class, h -> h.getOrder(order.getId()));
    }

    /**
     * Get an order by id
     *
     * @param id
     * @return order if found
     */
    @Override
    public Optional<WatchOrder> getOrder(UUID id) {
        WatchOrder order = jdbi.withExtension(OrderDao.class, h -> h.getOrder(id));

        return Optional.of(order);
    }
}
