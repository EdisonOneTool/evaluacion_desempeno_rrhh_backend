package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.ComportamientoRequest;
import com.edisonla.evaluacion_desempeno.entities.Comportamiento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComportamientoRequestMapper {
    Comportamiento toEntity(ComportamientoRequest dto);
    ComportamientoRequest toDto(Comportamiento entity);
}
