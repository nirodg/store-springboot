package com.example.eshop.rest.mappers;

import com.example.eshop.db.entities.Category;
import com.example.eshop.db.entities.Product;
import com.example.eshop.rest.model.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

//    @Mapping(source = "products", target = "productIds", qualifiedByName = "mapProductIds")
    CategoryDTO toDTO(Category category);

//    @Mapping(target = "products", ignore = true) // Avoid circular mapping
    Category toEntity(CategoryDTO categoryDTO);

    default List<Long> mapProductIds(List<Product> products) {
        return products.stream().map(Product::getId).toList();
    }
}
