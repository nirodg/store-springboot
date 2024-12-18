package com.example.eshop.db.services;

import com.example.eshop.common.AbstractService;
import com.example.eshop.db.entities.Product;
import com.example.eshop.db.repositories.ProductRepository;
import com.example.eshop.rest.mappers.ProductMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends AbstractService<Product, Long> {

    private final ProductMapper productMapper;

    public ProductService(ProductRepository repository, ProductMapper productMapper) {
        super(repository);
        this.productMapper = productMapper;
    }

}
