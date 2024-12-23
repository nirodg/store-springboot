package com.example.eshop.db.entities;


import com.example.eshop.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity(name = "Cart")
public class Cart extends AbstractEntity {


    /**
     * The user to whom this cart belongs.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * List of items in the cart.
     */
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items;

    /**
     * The total amount for the cart.
     */
    private BigDecimal totalAmount;
}