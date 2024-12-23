package com.example.eshop.common;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<ENTITY, ID> {

    protected final JpaRepository<ENTITY, ID> repository;

    protected AbstractService(JpaRepository<ENTITY, ID> repository) {
        this.repository = repository;
    }

    public List<ENTITY> findAll() {
        return repository.findAll().stream().toList();
    }

    public Optional<ENTITY> findById(ID id) {
        return repository.findById(id);
    }

    public ENTITY create(ENTITY entity) {
        return repository.save(entity);
    }

    public ENTITY update(ID id, ENTITY entity) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Entity not found");
        }
        return repository.save(entity);
    }

    public void deleteById(ID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Entity not found");
        }
        repository.deleteById(id);
    }
}
