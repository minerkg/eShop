package com.csiszer.my_shop.repository;

import com.csiszer.my_shop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CartRepository extends JpaRepository<Cart, Long> {
}
