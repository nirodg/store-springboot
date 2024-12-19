package com.example.eshop.db.entities;

import com.example.eshop.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "User")
@Table(name = "users") // Use "orders" instead of "order", since is a reserved keyword.
public class User extends AbstractEntity {

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