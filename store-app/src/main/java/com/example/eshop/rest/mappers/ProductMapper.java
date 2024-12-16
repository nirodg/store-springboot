package com.example.eshop.rest.mappers;


import com.example.eshop.db.entities.Product;
import com.example.eshop.rest.model.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

//    @Mapping(source = "category.id", target = "categoryId")
    ProductDTO toDTO(Product product);

//    @Mapping(target = "category", ignore = true) // Handle category mapping separately
    Product toEntity(ProductDTO productDTO);
}