package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.ComportamientoDto;
import com.edisonla.evaluacion_desempeno.entities.Comportamiento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComportamientoMapper {
    ComportamientoDto toDto(Comportamiento comportamiento);
}
