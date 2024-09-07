package com.csiszer.my_shop.service.cart;

import com.csiszer.my_shop.exceptions.ResourceNotFoundException;
import com.csiszer.my_shop.model.Cart;
import com.csiszer.my_shop.repository.CartItemRepository;
import com.csiszer.my_shop.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.csiszer.my_shop.model.CartItem;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService{

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);

        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(Long id) {
        Cart cart = getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        cart.getItems().clear();
        cartRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart = getCart(id);
        return cart.getItems()
                .stream()
                .map(CartItem :: getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal :: add);
    }
}
