package com.example.eshop.db.common;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * A base class for all entities in the e-commerce application.
 * This class provides common fields that can be inherited by other entity classes,
 * including an identifier and timestamps for creation and updates.
 */
@MappedSuperclass
@Data
public class AbstractEntity {

    /**
     * The unique identifier for the entity.
     * This field is automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The date and time when the entity was created.
     */
    private Date createdAt;

    /**
     * The date and time when the entity was last updated.
     */
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}