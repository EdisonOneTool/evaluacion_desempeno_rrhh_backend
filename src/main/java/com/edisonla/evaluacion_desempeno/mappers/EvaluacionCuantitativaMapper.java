package com.edisonla.evaluacion_desempeno.mappers;


import com.edisonla.evaluacion_desempeno.dtos.EvaluacionCuantitativaDto;
import com.edisonla.evaluacion_desempeno.entities.EvaluacionCuantitativa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvaluacionCuantitativaMapper {
    EvaluacionCuantitativaDto toDto(EvaluacionCuantitativa evaluacionCuantitativa);
}
