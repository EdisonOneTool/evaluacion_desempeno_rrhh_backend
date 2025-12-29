package com.edisonla.evaluacion_desempeno.dtos;

import java.util.List;

public record EvaluacionesResponse(
        List<EvaluacionDto> evaluaciones,
        List<EvaluacionDto> validaciones
) {}