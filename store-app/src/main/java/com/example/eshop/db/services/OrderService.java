package com.example.eshop.db.services;

import com.example.eshop.db.entities.Order;
import com.example.eshop.db.entities.enums.OrderStatus;
import com.example.eshop.db.repositories.OrderRepository;
import com.example.eshop.rest.common.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService extends AbstractCrudService<Order, Long> {

    public OrderService(OrderRepository repository) {
        super(repository);
    }

    public List<Order> getUserOrdersByStatus(Long userId, OrderStatus status) {
        return ((OrderRepository) repository).findAllByUserIdAndStatus(userId, status);
    }
}