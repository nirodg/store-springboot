package com.example.eshop.db.repositories;

import com.example.eshop.db.entities.Order;
import com.example.eshop.db.entities.User;
import com.example.eshop.db.entities.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserId(Long userId);

    List<Order> findAllByUserIdAndStatus(Long userId, OrderStatus status);

    /**
     * Finds all orders associated with a given user.
     *
     * @param user the user entity
     * @return a list of orders belonging to the user
     */
    List<Order> findAllByUser(User user);

    /**
     * Finds an order by its ID and associated user.
     *
     * @param orderId the ID of the order
     * @param user    the user entity
     * @return an Optional containing the order if found
     */
    Optional<Order> findByIdAndUser(Long orderId, User user);
}