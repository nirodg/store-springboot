package com.example.eshop.rest.mappers;

import com.example.eshop.db.entities.Payment;
import com.example.eshop.rest.model.PaymentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {
//    @Mapping(source = "order.id", target = "orderId")
    PaymentDTO toDTO(Payment payment);

//    @Mapping(target = "order", ignore = true) // Prevent circular mapping
    Payment toEntity(PaymentDTO paymentDTO);
}