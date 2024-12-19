package com.example.eshop.rest.mappers;

import com.example.eshop.common.AbstractMapper;
import com.example.eshop.db.entities.Cart;
import com.example.eshop.rest.model.CartDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper extends AbstractMapper<Cart, CartDTO> {

    @Mapping(source = "user.id", target = "userId")
    CartDTO toDTO(Cart cart);

}