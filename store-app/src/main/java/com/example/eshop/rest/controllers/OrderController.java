package com.example.eshop.rest.controllers;


import com.example.eshop.db.entities.Order;
import com.example.eshop.db.entities.enums.OrderStatus;
import com.example.eshop.db.services.OrderService;
import com.example.eshop.rest.mappers.CommonMapper;
import com.example.eshop.rest.mappers.OrderMapper;
import com.example.eshop.rest.model.OrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final CommonMapper commonMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper, CommonMapper commonMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.commonMapper = commonMapper;
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.findAll()
                .stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    // Get an order by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        return orderService.findById(orderId)
                .map(orderMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new order
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        order.setStatus(OrderStatus.PENDING); // Default status for new orders
        Order savedOrder = orderService.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderMapper.toDTO(savedOrder));
    }

    // Update an existing order (e.g., status update)
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        return orderService.findById(orderId)
                .map(existingOrder -> {
                    existingOrder.setStatus(commonMapper.toEntity(orderDTO.getStatus()));
                    existingOrder.setTotalAmount(orderDTO.getTotalAmount());
                    existingOrder.setOrderDate(orderDTO.getOrderDate());
                    existingOrder.setDeliveryAddress(commonMapper.toEntity(orderDTO.getDeliveryAddress()));

                    Order updatedOrder = orderService.save(existingOrder);
                    return ResponseEntity.ok(orderMapper.toDTO(updatedOrder));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete an order
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Object> deleteOrder(@PathVariable Long orderId) {
        return orderService.findById(orderId)
                .map(order -> {
                    orderService.deleteById(orderId);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}