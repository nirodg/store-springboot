package com.example.eshop.db.entities;

import com.example.eshop.db.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "User")
public class User extends AbstractEntity {

    /**
     * The unique identifier for the entity.
     * This field is automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String firstName;
    private String lastName;

    @Embedded
    private Address address; // Embedded address

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orderHistory;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> activeOrders;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Order currentOrder;

    private boolean active;
}