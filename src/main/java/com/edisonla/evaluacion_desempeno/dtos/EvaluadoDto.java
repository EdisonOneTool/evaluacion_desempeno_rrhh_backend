package com.edisonla.evaluacion_desempeno.dtos;

import java.time.LocalDateTime;

public record EvaluadoDto(Long id, String nombre, String apellido, LocalDateTime incorporación, String legajo, double resultadoFinal, String username, boolean esAdmin) {
}
