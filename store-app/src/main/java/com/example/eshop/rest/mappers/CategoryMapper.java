package com.example.eshop.rest.mappers;

import com.example.eshop.common.AbstractMapper;
import com.example.eshop.db.entities.Category;
import com.example.eshop.db.entities.Product;
import com.example.eshop.rest.model.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface CategoryMapper extends AbstractMapper<Category, CategoryDTO> {

    @Mapping(source = "products", target = "productIds", qualifiedByName = "mapProductIds")
    CategoryDTO toDTO(Category category);

    // Avoid circular mapping
    @Mapping(target = "products", ignore = true)
    Category toEntity(CategoryDTO categoryDTO);

    @Named("mapProductIds")
    default List<Long> mapProductIds(List<Product> products) {
        return products.stream().map(Product::getId).toList();
    }
}
