package com.example.eshop.rest.controllers;

import com.example.eshop.common.AbstractController;
import com.example.eshop.common.AbstractMapper;
import com.example.eshop.common.AbstractService;
import com.example.eshop.db.entities.Product;
import com.example.eshop.db.services.ProductService;
import com.example.eshop.rest.mappers.ProductMapper;
import com.example.eshop.rest.model.ProductDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController extends AbstractController<Product, ProductDTO, Long> {

    protected final ProductService productService;
    protected final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Override
    protected AbstractService<Product, Long> service() {
        return productService;
    }

    @Override
    protected AbstractMapper<Product, ProductDTO> mapper() {
        return productMapper;
    }
}
