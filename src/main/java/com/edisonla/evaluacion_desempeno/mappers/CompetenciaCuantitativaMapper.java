package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCuantitativaDto;
import com.edisonla.evaluacion_desempeno.entities.CompetenciaCuantitativa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetenciaCuantitativaMapper {
    CompetenciaCuantitativa toEntity(CompetenciaCuantitativaDto dto);
    CompetenciaCuantitativaDto toDto(CompetenciaCuantitativa entity);
}
