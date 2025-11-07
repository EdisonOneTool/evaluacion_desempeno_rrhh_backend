package com.edisonla.evaluacion_desempeno.dtos;

import java.util.Date;

public record EvaluadoDto(Long id, String nombre, String apellido, Date incorporacion, int legajo, double resultadoFinal, String username, String mail, String password, boolean esAdmin) {
}
