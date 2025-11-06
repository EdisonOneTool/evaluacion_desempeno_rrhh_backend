package com.edisonla.evaluacion_desempeno.dtos;

import java.time.LocalDateTime;

public record EvaluadoCreateDto(String nombre, String apellido, LocalDateTime incorporacion, int legajo, String username) {
}

