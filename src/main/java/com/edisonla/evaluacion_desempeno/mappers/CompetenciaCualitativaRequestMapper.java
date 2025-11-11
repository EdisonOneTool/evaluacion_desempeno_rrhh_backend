package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCualitativaRequest;
import com.edisonla.evaluacion_desempeno.entities.CompetenciaCualitativa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetenciaCualitativaRequestMapper {
    CompetenciaCualitativa toEntity(CompetenciaCualitativaRequest dto);
    CompetenciaCualitativaRequest toDto(CompetenciaCualitativa entity);
}
