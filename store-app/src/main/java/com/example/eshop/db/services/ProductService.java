package com.example.eshop.db.services;

import com.example.eshop.common.AbstractService;
import com.example.eshop.db.entities.Category;
import com.example.eshop.db.entities.Product;
import com.example.eshop.db.repositories.ProductRepository;
import com.example.eshop.rest.mappers.ProductMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService extends AbstractService<Product, Long> {

    private final ProductMapper productMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public ProductService(ProductRepository repository, ProductMapper productMapper) {
        super(repository);
        this.productMapper = productMapper;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public List<Product> findAllByCategory(Category category) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);

        cq.select(root).where(cb.equal(root.get("category"), category));

        return entityManager.createQuery(cq).getResultList();
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}