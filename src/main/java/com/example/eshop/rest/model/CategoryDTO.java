package com.example.eshop.rest.model;


import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private boolean visible;
    private boolean active;
    private List<Long> productIds; // References to products
}
