package com.edisonla.evaluacion_desempeno.dtos;

import java.time.LocalDate;

public record EvaluacionCuantitativaDto(Long id, String nombre, String descripcion, LocalDate fecha, double resultado) {
}
