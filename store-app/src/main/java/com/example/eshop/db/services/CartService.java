package com.example.eshop.db.services;

import com.example.eshop.common.AbstractService;
import com.example.eshop.db.entities.Cart;
import com.example.eshop.db.entities.CartItem;
import com.example.eshop.db.entities.Order;
import com.example.eshop.db.entities.enums.OrderStatus;
import com.example.eshop.db.repositories.CartRepository;
import com.example.eshop.db.repositories.OrderRepository;
import com.example.eshop.rest.mappers.CartMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CartService extends AbstractService<Cart, Long> {

    private final OrderRepository orderRepository;

    public CartService(CartRepository repository, OrderRepository orderRepository) {
        super(repository);
        this.orderRepository = orderRepository;
    }

    public Optional<Cart> findByUserId(Long userId) {
        return ((CartRepository) repository).findByUserId(userId);
    }

    public Cart addItemToCart(Long userId, CartItem cartItem) {
        Cart cart = findByUserId(userId).orElse(new Cart());
        cartItem.setCart(cart);
        cart.getItems().add(cartItem);
        return repository.save(cart);
    }

    public void removeItemFromCart(Long userId, Long itemId) {
        Cart cart = findByUserId(userId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getItems().removeIf(item -> item.getId().equals(itemId));
        repository.save(cart);
    }

    public Order checkout(Long userId) {
        Cart cart = findByUserId(userId).orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // Convert Cart to Order
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setTotalAmount(cart.getTotalAmount());
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(new Date());

        // Optionally: Save the order (OrderRepository must be injected)
        orderRepository.save(order);

        // Clear Cart
        cart.getItems().clear();
        repository.save(cart);

        return order;
    }

}