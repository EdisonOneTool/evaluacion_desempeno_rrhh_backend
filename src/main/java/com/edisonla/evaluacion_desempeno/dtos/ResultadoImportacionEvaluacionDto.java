package com.edisonla.evaluacion_desempeno.dtos;

import com.edisonla.evaluacion_desempeno.entities.Evaluacion;
import java.util.List;

public record ResultadoImportacionEvaluacionDto(
        int total,
        int creados,
        int errores,
        List<String> mensajesError,
        List<Evaluacion> evaluacionesCargadas
) {}