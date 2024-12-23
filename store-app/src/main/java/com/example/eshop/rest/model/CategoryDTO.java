package com.example.eshop.rest.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDTO extends AbstractEntityDTO{
    private String name;
    private String description;
    private String imageUrl;
    private boolean visible;
    private boolean active;
    private List<Long> productIds; // References to products
}
