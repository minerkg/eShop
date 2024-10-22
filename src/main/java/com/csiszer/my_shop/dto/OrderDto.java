package com.csiszer.my_shop.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderDto {
    private Long orderId;
    private Long userId;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private String orderStatus;
    private List<OrderItemDto> orderItems;

}
