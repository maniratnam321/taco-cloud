package com.solo.tacocloud.repository;

import com.solo.tacocloud.tacos.Order;

public interface OrderRepository {

    Order save(Order order);
}
