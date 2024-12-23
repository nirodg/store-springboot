package com.example.eshop.rest.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class AbstractEntityDTO {
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
}
