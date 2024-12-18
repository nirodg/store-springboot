package com.example.eshop.rest.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO extends AbstractEntityDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Long categoryId; // Reference to category
    private boolean active;
}
