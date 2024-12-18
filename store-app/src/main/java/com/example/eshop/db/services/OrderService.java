package com.example.eshop.db.services;

import com.example.eshop.db.entities.Order;
import com.example.eshop.db.entities.enums.OrderStatus;
import com.example.eshop.db.repositories.OrderRepository;
import com.example.eshop.common.AbstractService;
import com.example.eshop.rest.mappers.OrderMapper;
import com.example.eshop.rest.model.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService extends AbstractService<Order, Long> {

    private final OrderMapper orderMapper;

    public OrderService(OrderRepository repository, OrderMapper orderMapper) {
        super(repository);
        this.orderMapper = orderMapper;
    }

    public List<Order> getUserOrdersByStatus(Long userId, OrderStatus status) {
        return ((OrderRepository) repository).findAllByUserIdAndStatus(userId, status);
    }
}