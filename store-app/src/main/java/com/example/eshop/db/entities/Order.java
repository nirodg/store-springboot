package com.example.eshop.db.entities;

import com.example.eshop.db.common.AbstractEntity;
import com.example.eshop.db.entities.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity(name = "Order")
@Table(name = "orders") // Use "orders" instead of "order", since is a reserved keyword.
public class Order extends AbstractEntity {
    /**
     * The unique identifier for the entity.
     * This field is automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Reference to the user who owns this order.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Order status (e.g., PAID, CANCELLED, COMPLETED, etc.).
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    /**
     * Total amount for the order.
     */
    private BigDecimal totalAmount;

    /**
     * Delivery address for the order.
     */
    @Embedded
    private Address deliveryAddress;

    /**
     * Order creation date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
}