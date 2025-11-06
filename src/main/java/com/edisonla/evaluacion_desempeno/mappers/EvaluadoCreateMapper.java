package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.EvaluadoCreateDto;
import com.edisonla.evaluacion_desempeno.entities.Evaluado;

public class EvaluadoCreateMapper {
    private EvaluadoCreateMapper() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

    public static Evaluado toEntity(final EvaluadoCreateDto dto) {
        return new Evaluado(dto.nombre(), dto.apellido(), dto.incorporacion(), dto.legajo(), dto.username());
    }

    public static EvaluadoCreateDto toDto(final Evaluado evaluado) {
        return new EvaluadoCreateDto(
                evaluado.getNombre(),
                evaluado.getApellido(),
                evaluado.getIncorporacion(),
                evaluado.getLegajo(),
                evaluado.getUsername()
        );
    }
    //String nombre, String apellido, LocalDateTime incorporacion, int legajo, String username
}
