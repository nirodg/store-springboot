package com.example.eshop.rest.model;

import com.example.eshop.rest.model.enums.PaymentStatusDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDTO extends AbstractEntityDTO{
    private Long orderId; // Reference to the Order
    private PaymentStatusDTO status; // Payment status
    private String paymentMethod; // CREDIT_CARD, PAYPAL, etc.
    private String transactionId; // Unique transaction ID
    private BigDecimal amount;
}