package com.example.eshop.rest.controllers.admin;

import com.example.eshop.common.AbstractController;
import com.example.eshop.common.AbstractMapper;
import com.example.eshop.common.AbstractService;
import com.example.eshop.db.entities.Payment;
import com.example.eshop.db.services.PaymentService;
import com.example.eshop.rest.mappers.PaymentMapper;
import com.example.eshop.rest.model.PaymentDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController extends AbstractController<Payment, PaymentDTO, Long> {

    protected final PaymentService paymentService;
    protected final PaymentMapper paymentMapper;

    public PaymentController(PaymentService paymentService, PaymentMapper paymentMapper) {
        this.paymentService = paymentService;
        this.paymentMapper = paymentMapper;
    }

    @Override
    protected AbstractService<Payment, Long> service() {
        return paymentService;
    }

    @Override
    protected AbstractMapper<Payment, PaymentDTO> mapper() {
        return paymentMapper;
    }
}