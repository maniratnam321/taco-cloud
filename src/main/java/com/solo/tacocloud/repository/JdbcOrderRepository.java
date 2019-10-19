package com.solo.tacocloud.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solo.tacocloud.tacos.Order;
import com.solo.tacocloud.tacos.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository{

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.orderInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("taco_order").usingGeneratedKeyColumns("id");
        this.orderTacoInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("taco_order_tacos");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(Instant.now());
        long orderId = saveOrderDetails(order);
        order.setId(orderId);
        for(Taco taco: order.getOrderedTacos()) {
            saveTacoOrdered(taco, orderId);
        }
        return order;
    }

    private void saveTacoOrdered(Taco taco, long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("taco", taco.getId());
        values.put("taco_order", orderId);
        orderTacoInserter.execute(values);
    }

    private long saveOrderDetails(Order order) {
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("placedAt", new Date(order.getPlacedAt().toEpochMilli()));
        long orderId = orderInserter.executeAndReturnKey(values).longValue();
        return orderId;
    }
}
