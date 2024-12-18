package com.example.eshop.rest.controllers;

import com.example.eshop.db.entities.Order;
import com.example.eshop.db.entities.enums.OrderStatus;
import com.example.eshop.db.services.OrderService;
import com.example.eshop.db.services.UserService;
import com.example.eshop.rest.mappers.UserMapper;
import com.example.eshop.rest.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final OrderService orderService;
    private final UserMapper userMapper;

    public UserController(UserService userService, OrderService orderService, UserMapper userMapper) {
        this.userService = userService;
        this.orderService = orderService;
        this.userMapper = userMapper;
    }

    // Get all user details by user ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        return userService.findById(userId)
                .map(user -> ResponseEntity.ok(userMapper.toDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get user order history (Completed Orders)
    @GetMapping("/{userId}/orders/history")
    public ResponseEntity<List<Long>> getOrderHistory(@PathVariable Long userId) {
        List<Long> orderHistoryIds = orderService.getUserOrdersByStatus(userId, OrderStatus.COMPLETED)
                .stream()
                .map(order -> order.getId())
                .collect(Collectors.toList());

        return ResponseEntity.ok(orderHistoryIds);
    }

    // Get active orders (Paid but not fulfilled)
    @GetMapping("/{userId}/orders/active")
    public ResponseEntity<List<Long>> getActiveOrders(@PathVariable Long userId) {
        List<Long> activeOrderIds = orderService.getUserOrdersByStatus(userId, OrderStatus.PAID)
                .stream()
                .map(order -> order.getId())
                .collect(Collectors.toList());

        return ResponseEntity.ok(activeOrderIds);
    }

    @GetMapping("/{userId}/orders/current")
    public ResponseEntity<Long> getCurrentOrder(@PathVariable Long userId) {
        return userService.findById(userId)
                .map(user -> user.getCurrentOrder()) // Explicitly get the Order
                .map(Order::getId) // Extract the ID from the Order object
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}