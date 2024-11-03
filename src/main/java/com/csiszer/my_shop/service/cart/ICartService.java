package com.csiszer.my_shop.service.cart;

import com.csiszer.my_shop.model.Cart;
import com.csiszer.my_shop.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Cart initializeNewCart(User user);

    Cart getCartByUserId(Long userId);
}
