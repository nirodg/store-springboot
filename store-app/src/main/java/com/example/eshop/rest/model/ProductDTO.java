package com.example.eshop.rest.model;

import com.example.eshop.db.entities.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductDTO extends AbstractEntityDTO {

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
    private Long categoryId; // Reference to category

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
