package com.csiszer.my_shop.repository;

import com.csiszer.my_shop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
