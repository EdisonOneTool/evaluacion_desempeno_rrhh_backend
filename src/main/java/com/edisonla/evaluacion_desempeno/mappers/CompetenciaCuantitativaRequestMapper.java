package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCuantitativaRequest;
import com.edisonla.evaluacion_desempeno.entities.CompetenciaCuantitativa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetenciaCuantitativaRequestMapper {
    CompetenciaCuantitativa toEntity(CompetenciaCuantitativaRequest dto);
    CompetenciaCuantitativaRequest toDto(CompetenciaCuantitativa entity);
}
