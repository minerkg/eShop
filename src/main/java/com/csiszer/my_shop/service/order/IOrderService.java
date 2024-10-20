package com.csiszer.my_shop.service.order;

import com.csiszer.my_shop.model.Order;

import java.util.List;

public interface IOrderService {

    Order placeOrder(Long userId);
    Order getOrder(Long orderId);

    List<Order> getUsersOrders(Long userId);
}
