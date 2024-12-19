package com.example.eshop.rest.mappers;

import com.example.eshop.common.AbstractMapper;
import com.example.eshop.db.entities.Order;
import com.example.eshop.rest.model.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CommonMapper.class})
public interface OrderMapper extends AbstractMapper<Order, OrderDTO> {

    @Mapping(source = "user.id", target = "userId")
    OrderDTO toDTO(Order order);

    // Avoid circular reference
    @Mapping(target = "user", ignore = true)
    Order toEntity(OrderDTO orderDTO);

}