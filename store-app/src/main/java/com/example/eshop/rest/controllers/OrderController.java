package com.example.eshop.rest.controllers;


import com.example.eshop.common.AbstractController;
import com.example.eshop.common.AbstractMapper;
import com.example.eshop.common.AbstractService;
import com.example.eshop.db.entities.Order;
import com.example.eshop.db.services.OrderService;
import com.example.eshop.rest.mappers.OrderMapper;
import com.example.eshop.rest.model.OrderDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends AbstractController<Order, OrderDTO, Long> {

    protected final OrderService orderService;
    protected final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @Override
    protected AbstractService<Order, Long> service() {
        return orderService;
    }

    @Override
    protected AbstractMapper<Order, OrderDTO> mapper() {
        return orderMapper;
    }
}