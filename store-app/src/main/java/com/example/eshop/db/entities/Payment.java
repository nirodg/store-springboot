package com.example.eshop.db.entities;

import com.example.eshop.db.common.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "Payment")
public class Payment extends AbstractEntity {

    private String paymentMethod; // e.g., CREDIT_CARD, PAYPAL

    private String transactionId;

    private String status; // e.g., SUCCESS, FAILED

    private BigDecimal amount;
}