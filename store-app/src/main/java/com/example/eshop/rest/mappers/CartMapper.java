package com.example.eshop.rest.mappers;

import com.example.eshop.db.entities.Cart;
import com.example.eshop.rest.model.CartDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartMapper {
//    @Mapping(source = "user.id", target = "userId")
    CartDTO toDTO(Cart cart);

    Cart toEntity(CartDTO cartDTO);
}