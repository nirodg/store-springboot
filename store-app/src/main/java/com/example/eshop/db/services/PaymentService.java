package com.example.eshop.db.services;

import com.example.eshop.common.AbstractService;
import com.example.eshop.db.entities.Payment;
import com.example.eshop.db.repositories.PaymentRepository;
import com.example.eshop.rest.mappers.PaymentMapper;
import org.springframework.stereotype.Service;

@Service
public class PaymentService extends AbstractService<Payment, Long> {

    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentRepository repository, PaymentMapper paymentMapper) {
        super(repository);
        this.paymentMapper = paymentMapper;
    }

}