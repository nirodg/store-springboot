package com.example.eshop.db.services;

import com.example.eshop.db.entities.Product;
import com.example.eshop.db.repositories.ProductRepository;
import com.example.eshop.rest.common.AbstractCrudService;
import com.example.eshop.rest.mappers.ProductMapper;
import com.example.eshop.rest.model.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends AbstractCrudService<Product, Long> {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, ProductRepository repository) {
        super(repository);
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .toList();
    }

    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        return productMapper.toDTO(productRepository.save(product));
    }
}
