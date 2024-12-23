package com.example.eshop.db.services;

import com.example.eshop.db.entities.Order;
import com.example.eshop.db.entities.User;
import com.example.eshop.db.entities.enums.OrderStatus;
import com.example.eshop.db.repositories.OrderRepository;
import com.example.eshop.common.AbstractService;
import com.example.eshop.rest.mappers.OrderMapper;
import com.example.eshop.rest.model.OrderDTO;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService extends AbstractService<Order, Long> {

    public OrderService(OrderRepository repository) {
        super(repository);
    }

    public List<Order> getUserOrdersByStatus(Long userId, OrderStatus status) {
        return ((OrderRepository) repository).findAllByUserIdAndStatus(userId, status);
    }

    public List<Order> findAllByUser(User user) {
        return ((OrderRepository) repository).findAllByUser(user);
    }

    public Optional<Order> findByIdAndUser(Long orderId, User user) {
        return ((OrderRepository) repository).findByIdAndUser(orderId, user);
    }
}