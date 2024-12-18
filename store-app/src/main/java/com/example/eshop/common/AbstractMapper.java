package com.example.eshop.common;

import java.util.List;
import java.util.Optional;

/**
 * Abstract interface for mapping ENTITY <> DTO
 * @param <ENTITY>
 * @param <DTO>
 */
public interface AbstractMapper<ENTITY, DTO> {

    ENTITY toEntity(DTO dto);
    List<ENTITY> toList(List<DTO> DTOs);

    DTO toDTO(ENTITY entity);
//    Optional<DTO> toOptionalDTO(Optional<ENTITY> entity);
    List<DTO> toDTOs(List<ENTITY> entities);

}
