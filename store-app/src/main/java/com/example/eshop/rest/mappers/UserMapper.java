package com.example.eshop.rest.mappers;

import com.example.eshop.common.AbstractMapper;
import com.example.eshop.db.entities.Order;
import com.example.eshop.db.entities.User;
import com.example.eshop.rest.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CommonMapper.class})
public interface UserMapper  extends AbstractMapper<User, UserDTO> {

    // Map User entity to UserDTO
    @Mapping(source = "activeOrders", target = "activeOrderIds", qualifiedByName = "mapOrderIds")
    @Mapping(source = "orderHistory", target = "orderHistoryIds", qualifiedByName = "mapOrderIds")
    @Mapping(source = "currentOrder.id", target = "currentOrderId")
    @Mapping(source = "address", target = "address")
    UserDTO toDTO(User user);


    // Map UserDTO to User entity (excluding orders to avoid circular references)
    @Mapping(target = "activeOrders", ignore = true)
    @Mapping(target = "orderHistory", ignore = true)
    @Mapping(target = "currentOrder", ignore = true)
    @Mapping(target = "address", ignore = true)
    User toEntity(UserDTO userDTO);

    // Helper method to map list of orders to their IDs
    @Named("mapOrderIds")
    default List<Long> mapOrderIds(List<Order> orders) {
        return orders != null
                ? orders.stream().map(Order::getId).collect(Collectors.toList())
                : null;
    }
}