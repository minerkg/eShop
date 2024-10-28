package com.csiszer.my_shop.service.order;

import com.csiszer.my_shop.dto.OrderDto;
import com.csiszer.my_shop.model.Order;

import java.util.List;

public interface IOrderService {

    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);

    List<OrderDto> getUsersOrders(Long userId);
}
