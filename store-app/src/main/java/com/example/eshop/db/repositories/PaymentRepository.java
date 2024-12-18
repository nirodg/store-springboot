package com.example.eshop.db.repositories;

import com.example.eshop.db.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
