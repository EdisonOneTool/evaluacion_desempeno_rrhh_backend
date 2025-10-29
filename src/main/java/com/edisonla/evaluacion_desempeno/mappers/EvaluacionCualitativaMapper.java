package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.EvaluacionCualitativaDto;
import com.edisonla.evaluacion_desempeno.entities.EvaluacionCualitativa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvaluacionCualitativaMapper {
    EvaluacionCualitativaDto toDto(EvaluacionCualitativa evaluacionCualitativa);
}
