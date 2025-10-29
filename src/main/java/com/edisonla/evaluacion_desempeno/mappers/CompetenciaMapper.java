package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.CompetenciaDto;
import com.edisonla.evaluacion_desempeno.entities.Competencia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetenciaMapper {
    CompetenciaDto toDto(Competencia competencia);
}
