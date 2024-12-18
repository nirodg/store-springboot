package com.example.eshop.db.entities;


import com.example.eshop.common.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "CartItem")
public class CartItem extends AbstractEntity {

    /**
     * The cart to which this item belongs.
     */
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    /**
     * The product being added to the cart.
     */
    @Column(nullable = false)
    private Long productId;

    /**
     * Quantity of the product.
     */
    private Integer quantity;

    /**
     * Price of the product at the time of addition.
     */
    private BigDecimal price;
}