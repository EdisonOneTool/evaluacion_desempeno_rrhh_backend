package com.edisonla.evaluacion_desempeno.dtos;

import java.time.LocalDate;

public record NominaEvaluacionDto(
        int legajoEvaluado,
        int legajoEvaluador,
        int legajoValidador,
        String nombre,
        LocalDate fecha,
        String puesto,
        String celula,
        String disponibilidad
) {}