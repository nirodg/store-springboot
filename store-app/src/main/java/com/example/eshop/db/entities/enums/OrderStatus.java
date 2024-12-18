package com.example.eshop.db.entities.enums;

public enum OrderStatus {
    PENDING,      // Order created but payment not yet confirmed
    PAID,         // Payment successful, waiting for fulfillment
    PROCESSING,   // Order is being processed (picked, packed, etc.)
    SHIPPED,      // Order shipped to the customer
    DELIVERED,    // Order delivered successfully
    COMPLETED,    // Order completed (same as DELIVERED, but final state)
    CANCELLED,    // Order cancelled by the user or admin
    FAILED,       // Payment or fulfillment failed
    RETURNED,     // Order was returned by the customer
    REFUNDED      // Payment refunded to the customer
}