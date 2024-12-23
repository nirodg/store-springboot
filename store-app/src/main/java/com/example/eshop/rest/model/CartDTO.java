package com.example.eshop.rest.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CartDTO extends AbstractEntityDTO {
    private Long userId; // Reference to the User
    private List<CartItemDTO> items;
    private BigDecimal totalAmount;
}
