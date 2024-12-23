package com.example.eshop.db.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {

    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}