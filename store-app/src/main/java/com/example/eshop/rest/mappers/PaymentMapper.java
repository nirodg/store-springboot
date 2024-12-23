package com.example.eshop.rest.mappers;

import com.example.eshop.common.AbstractMapper;
import com.example.eshop.db.entities.Payment;
import com.example.eshop.rest.model.PaymentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper extends AbstractMapper<Payment, PaymentDTO> {
    @Mapping(target = "orderId", source = "order.id")
    PaymentDTO toDTO(Payment payment);

    // Prevent circular mapping
    @Mapping(target = "order", ignore = true)
    Payment toEntity(PaymentDTO paymentDTO);
}