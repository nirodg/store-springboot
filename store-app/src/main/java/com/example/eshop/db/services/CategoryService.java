package com.example.eshop.db.services;

import com.example.eshop.db.entities.Category;
import com.example.eshop.db.repositories.CategoryRepository;
import com.example.eshop.common.AbstractService;
import com.example.eshop.rest.mappers.CategoryMapper;
import com.example.eshop.rest.model.CategoryDTO;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends AbstractService<Category, Long> {

    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository repository, CategoryMapper categoryMapper) {
        super(repository);
        this.categoryMapper = categoryMapper;
    }

}

