package com.example.eshop.rest.model;

import com.example.eshop.rest.model.enums.OrderStatusDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class OrderDTO extends AbstractEntityDTO{
    private Long userId; // Reference to the User
    private OrderStatusDTO status; // Order status
    private BigDecimal totalAmount;
    private AddressDTO deliveryAddress;
    private Date orderDate;
}

