package com.example.eshop.db.entities;

import com.example.eshop.common.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents a category in the e-commerce application.
 * This class contains attributes that define a product category,
 * including its name, description, associated products, and visibility status.
 */
@Getter
@Setter
@Entity(name = "Category")
public class Category extends AbstractEntity {

    /**
     * The name of the category.
     */
    private String name;

    /**
     * A detailed description of the category.
     */
    private String description;

    /**
     * The URL or path to an image representing the category.
     */
    private String imageUrl;

    /**
     * The parent category of this category, if applicable.
     * This allows for hierarchical categorization of products.
     */

//    private Category parent;

    /**
     * A list of products that belong to this category.
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Product> products;

    /**
     * Indicates whether the category is visible to customers.
     */
    private boolean visible;

    /**
     * Indicates whether the category is active and can be used in the application.
     */
    private boolean active;
}