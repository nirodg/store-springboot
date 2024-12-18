package com.example.eshop.db.services;

import com.example.eshop.db.entities.Payment;
import com.example.eshop.db.repositories.PaymentRepository;
import com.example.eshop.rest.common.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class PaymentService extends AbstractCrudService<Payment, Long> {

    public PaymentService(PaymentRepository repository) {
        super(repository);
    }
}