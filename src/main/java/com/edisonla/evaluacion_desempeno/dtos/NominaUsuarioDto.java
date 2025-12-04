package com.edisonla.evaluacion_desempeno.dtos;

import java.util.Date;

    public record NominaUsuarioDto(
            int legajo,
            String cuil,
            String nombre,
            String apellido,
            String email,
            Date fechaInco,
            String puesto,
            String celula
    ) {}

