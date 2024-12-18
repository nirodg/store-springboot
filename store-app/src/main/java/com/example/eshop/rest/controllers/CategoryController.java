package com.example.eshop.rest.controllers;

import com.example.eshop.common.AbstractMapper;
import com.example.eshop.common.AbstractService;
import com.example.eshop.db.entities.Category;
import com.example.eshop.db.services.CategoryService;
import com.example.eshop.common.AbstractController;
import com.example.eshop.rest.mappers.CategoryMapper;
import com.example.eshop.rest.model.CategoryDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController extends AbstractController<Category, CategoryDTO, Long> {

    protected final CategoryService categoryService;
    protected final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @Override
    protected AbstractService<Category, Long> service() {
        return categoryService;
    }

    @Override
    protected AbstractMapper<Category, CategoryDTO> mapper() {
        return categoryMapper;
    }
}
