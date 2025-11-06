package com.edisonla.evaluacion_desempeno.dtos;

import java.util.Date;

public record CompetenciaCuantitativaRequest(String nombre, String descripcion, Date fecha, double resultado,
                                             boolean validado) {
}
