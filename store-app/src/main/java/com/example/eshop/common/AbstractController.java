package com.example.eshop.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class AbstractController<ENTITY, DTO, ID> {

    protected abstract AbstractService<ENTITY, ID> service();

    protected abstract AbstractMapper<ENTITY, DTO> mapper();

    @GetMapping
    public ResponseEntity<List<DTO>> getAll() {
        return ResponseEntity.ok(mapper().toDTOs(service().findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO> getById(@PathVariable ID id) {
        Optional<DTO> entityByIdDto = Optional.of(mapper().toDTO((ENTITY) service().findById(id)));
        return entityByIdDto
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DTO> create(@RequestBody DTO dto) {
        ENTITY created = service().create(mapper().toEntity(dto));
        return ResponseEntity.status(201).body(mapper().toDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTO> update(@PathVariable ID id, @RequestBody DTO dto) {
        ENTITY updated = service().update(id, mapper().toEntity(dto));
        return ResponseEntity.ok(mapper().toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service().deleteById(id);
        return ResponseEntity.noContent().build();
    }

}