package com.example.eshop.rest.controllers;

import com.example.eshop.db.entities.Payment;
import com.example.eshop.db.services.PaymentService;
import com.example.eshop.rest.mappers.PaymentMapper;
import com.example.eshop.rest.model.PaymentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    public PaymentController(PaymentService paymentService, PaymentMapper paymentMapper) {
        this.paymentService = paymentService;
        this.paymentMapper = paymentMapper;
    }

    // Get all payments
    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        List<PaymentDTO> payments = paymentService.findAll()
                .stream()
                .map(paymentMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(payments);
    }

    // Get payment by ID
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long paymentId) {
        return paymentService.findById(paymentId)
                .map(paymentMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new payment
    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        Payment payment = paymentMapper.toEntity(paymentDTO);
        Payment savedPayment = paymentService.save(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentMapper.toDTO(savedPayment));
    }

    // Update an existing payment
    @PutMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable Long paymentId, @RequestBody PaymentDTO paymentDTO) {
        return paymentService.findById(paymentId)
                .map(existingPayment -> {
                    // Update the status and payment method
                    existingPayment.setStatus(String.valueOf(paymentDTO.getStatus())); // FIXME
                    existingPayment.setPaymentMethod(paymentDTO.getPaymentMethod());
                    existingPayment.setTransactionId(paymentDTO.getTransactionId());
                    existingPayment.setAmount(paymentDTO.getAmount());

                    Payment updatedPayment = paymentService.save(existingPayment);
                    return ResponseEntity.ok(paymentMapper.toDTO(updatedPayment));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a payment
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Object> deletePayment(@PathVariable Long paymentId) {
        return paymentService.findById(paymentId)
                .map(payment -> {
                    paymentService.deleteById(paymentId);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}