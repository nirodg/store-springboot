package com.example.eshop.rest.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItemDTO extends AbstractEntityDTO{
    private Long productId; // Reference to the Product
    private Integer quantity;
    private BigDecimal price; // Price at the time of addition
}
