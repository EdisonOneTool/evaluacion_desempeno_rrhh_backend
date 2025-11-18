package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.ComportamientoDto;
import com.edisonla.evaluacion_desempeno.entities.Comportamiento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComportamientoMapper {
    Comportamiento toEntity(ComportamientoDto dto);
    ComportamientoDto toDto(Comportamiento entity);
}

