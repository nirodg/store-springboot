package com.example.eshop.rest.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO extends AbstractEntityDTO{
    private String email;
    private String firstName;
    private String lastName;
    private boolean active;

    private AddressDTO address; // User's address

    private List<Long> activeOrderIds; // IDs of active orders
    private List<Long> orderHistoryIds; // IDs of past orders
    private Long currentOrderId; // ID of the current order
}