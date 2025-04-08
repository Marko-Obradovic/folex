package com.folex.orderservice.watch;

import com.folex.orderservice.model.WatchOrder;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.UUID;

public interface OrderDao {

    @SqlUpdate("INSERT INTO watch_orders(id, status, order_date, source_id, description, last_update) " +
            "VALUES (:id, :order.status, now(), :sourceId, :order.description, now())")
    void insertOrder(@BindBean("order")WatchOrder order, @Bind("sourceId")UUID sourceId, @Bind("id") UUID id);

    @SqlQuery("SELECT id FROM order_source WHERE source_name = :source LIMIT 1")
    UUID getSourceId(@Bind("source") String sourceName);


    @SqlQuery("select w.id, w.status, w.description, w.order_date, w.last_update, s.source_name as source from watch_orders w left join order_source s on s.id = w.source_id  where w.id = :id")
    @RegisterConstructorMapper(WatchOrder.class)
    WatchOrder getOrder(@Bind("id")UUID id);

    @SqlUpdate("UPDATE watch_orders SET status=:w.status, last_update=NOW() WHERE id=:w.id")
    void updateOrder(@BindBean("w") WatchOrder watchOrder);

}
