package com.edisonla.evaluacion_desempeno.dtos;

import java.util.List;

public record ResultadoImportacionDto(
        int total,
        int creados,
        int actualizados,
        int errores,
        List<String> mensajeError
) {}
