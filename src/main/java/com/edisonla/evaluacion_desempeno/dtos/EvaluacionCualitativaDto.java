package com.edisonla.evaluacion_desempeno.dtos;

import java.time.LocalDate;

public record EvaluacionCualitativaDto(Long id, String nombre, String descripcion, LocalDate fecha) {
}
