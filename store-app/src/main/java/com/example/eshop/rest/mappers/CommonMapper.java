package com.example.eshop.rest.mappers;

import com.example.eshop.db.entities.Address;
import com.example.eshop.db.entities.enums.OrderStatus;
import com.example.eshop.rest.model.AddressDTO;
import com.example.eshop.rest.model.enums.OrderStatusDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommonMapper {
    OrderStatus toEntity(OrderStatusDTO dto);

    OrderStatusDTO toDto(OrderStatus entity);

    Address toEntity(AddressDTO dto);

    AddressDTO toDto(Address entity);

}