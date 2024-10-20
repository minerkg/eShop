package com.csiszer.my_shop.service.order;

import com.csiszer.my_shop.enums.OrderStatus;
import com.csiszer.my_shop.exceptions.ResourceNotFoundException;
import com.csiszer.my_shop.model.Cart;
import com.csiszer.my_shop.model.Order;
import com.csiszer.my_shop.model.OrderItem;
import com.csiszer.my_shop.model.Product;
import com.csiszer.my_shop.repository.OrderRepository;
import com.csiszer.my_shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    @Override
    public Order placeOrder(Long userId) {
        return null;
        //to complit after user
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemList) {
        return orderItemList
                .stream()
                .map(item -> item.getPrice()
                        .multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Order createOrder(Cart cart) {
        Order order = new Order();
        //set the user ...
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return order;
    }


    private List<OrderItem> createOrdeItemes(Order order, Cart cart) {
        return cart.getItems().stream().map(cartItem -> {
            Product product = cartItem.getProduct();
            product.setInventory(product.getInventory() - cartItem.getQuantity());
            productRepository.save(product);
            return new OrderItem(
                    order,
                    product,
                    cartItem.getQuantity(),
                    cartItem.getUnitPrice()
            );
        }).toList();
    }



    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }


}
