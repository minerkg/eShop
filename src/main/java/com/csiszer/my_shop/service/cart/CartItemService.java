package com.csiszer.my_shop.service.cart;


import com.csiszer.my_shop.exceptions.ResourceNotFoundException;
import com.csiszer.my_shop.model.Cart;
import com.csiszer.my_shop.model.CartItem;
import com.csiszer.my_shop.model.Product;
import com.csiszer.my_shop.repository.CartItemRepository;
import com.csiszer.my_shop.repository.CartRepository;
import com.csiszer.my_shop.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService{

    private final CartItemRepository cartItemRepository;
    private final IProductService productService;
    private final ICartService cartService;
    private final CartRepository cartRepository;


    @Override
    public void addItemToCart(Long cartId, Long productId, int quantity) {
        //get the cart
        //get the product
        //check if the product already in the cart
        //if yes, increase the quantity with the requested quantity
        //if no , then initiate a new cart item entry
        Cart cart = cartService.getCart(cartId);
        Product product =productService.getProductById(productId);
        CartItem cartItem = cart.getItems()
                .stream()
                .filter(cartIt -> cartIt.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(new CartItem());

        if (cartItem.getId() == null) {
            cartItem.setCart(cart);
            cartItem.setQuantity(quantity);
            cartItem.setProduct(product);
            cartItem.setUnitPrice(product.getPrice());
        } else {
            var oldQuantity = cartItem.getQuantity();
            cartItem.setQuantity(oldQuantity + quantity);
        }
        cartItem.setTotalPrice();
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        CartItem itemToRemove = getCartItem(cartId, productId);
        cart.removeItem(itemToRemove);
        cartRepository.save(cart);
    }

    @Override
    public void updateProductQuantity(Long cartId, Long productId, int quantity) {
        Cart cart = cartService.getCart(cartId);
        cart.getItems().stream()
                .filter(cartIt -> cartIt.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(item -> {
                    item.setQuantity(quantity);
                    item.setUnitPrice(item.getProduct().getPrice());
                    item.setTotalPrice();
                });
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);

    }

    @Override
    public CartItem getCartItem(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        return cart.getItems()
                .stream()
                .filter(item -> item.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
}
