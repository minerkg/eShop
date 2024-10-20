package com.csiszer.my_shop.service.order;

import com.csiszer.my_shop.model.Order;

public interface IOrderService {

    Order placeOrder(Long userId);
    Order getOrder(Long orderId);
}
