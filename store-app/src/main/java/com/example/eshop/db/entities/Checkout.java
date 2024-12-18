package com.example.eshop.db.entities;

import com.example.eshop.db.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "Checkout")
public class Checkout extends AbstractEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    private String paymentMethod; // e.g., CREDIT_CARD, PAYPAL, etc.

    private String status; // e.g., PENDING, COMPLETED, FAILED

    @Embedded
    private Address deliveryAddress;

    private BigDecimal totalAmount;
}