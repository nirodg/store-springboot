package com.example.eshop.db.entities.enums;

public enum PaymentStatus {
    PENDING,        // Payment is pending (initiated but not completed)
    SUCCESS,        // Payment completed successfully
    FAILED,         // Payment failed (e.g., insufficient funds)
    REFUNDED,       // Payment refunded to the customer
    CANCELLED,      // Payment was cancelled
    AUTHORIZED,     // Payment authorized but not yet captured
    CAPTURED,       // Authorized payment captured successfully
    EXPIRED         // Payment authorization expired
}