package com.example.eshop.db.repositories;

import com.example.eshop.db.entities.Order;
import com.example.eshop.db.entities.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);

    List<Order> findAllByUserIdAndStatus(Long userId, OrderStatus status);
}