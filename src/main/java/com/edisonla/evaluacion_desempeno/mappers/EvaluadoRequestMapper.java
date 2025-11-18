package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.EvaluadoRequest;
import com.edisonla.evaluacion_desempeno.entities.Evaluado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvaluadoRequestMapper {
    Evaluado toEntity(EvaluadoRequest dto);
    EvaluadoRequest toDto(Evaluado entity);
}
