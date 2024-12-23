package com.example.eshop.db.entities;

import com.example.eshop.common.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents a product in the e-commerce application.
 * This class contains all the necessary attributes to define a product,
 * including its name, description, price, stock amount, and other relevant details.
 */
@Getter
@Setter
@Entity
public class Product extends AbstractEntity {

    /**
     * The name of the product.
     */
    private String name;

    /**
     * A detailed description of the product.
     */
    private String description;

    /**
     * The price of the product.
     */
    private double price;

    /**
     * The stock keeping unit (SKU) for the product, which is a unique identifier.
     */
    private String sku;

    /**
     * The amount of stock available for the product.
     */
    private int stockAmount;

    /**
     * The category to which the product belongs.
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * Indicates whether the product is available for purchase.
     */
    private boolean available;

    /**
     * Indicates whether the product has been marked as deleted.
     */
    private boolean deleted;

    /**
     * Indicates whether the product is visible to customers.
     */
    private boolean visible;

    /**
     * The discount applicable to the product, represented as a BigDecimal.
     */
    private BigDecimal discount;

    /**
     * The weight of the product, represented as a BigDecimal.
     */
    private BigDecimal weight;

    /**
     * The dimensions of the product, typically in a string format (e.g., "10x5x3 cm").
     */
    private String dimensions;

    /**
     * The URL or path to the product's thumbnail image.
     */
    private String thumbnail;

    /**
     * A list of URLs or paths to additional images of the product.
     */
    private List<String> images;
}