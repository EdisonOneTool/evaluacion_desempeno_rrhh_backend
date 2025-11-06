package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.EvaluadoDto;
import com.edisonla.evaluacion_desempeno.entities.Evaluado;

public class EvaluadoMapper {
    private EvaluadoMapper() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

    public static Evaluado toEntity(final EvaluadoDto dto) {
        return new Evaluado(dto.id(), dto.nombre(), dto.apellido(), dto.incorporacion(), dto.legajo(), dto.resultadoFinal(), dto.username(), dto.esAdmin());
    }

    public static EvaluadoDto toDto(final Evaluado entity) {
        return new EvaluadoDto(
                entity.getId(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getIncorporacion(),
                entity.getLegajo(),
                entity.getResultadoFinal(),
                entity.getUsername(),
                entity.isEsAdmin()
        );
        //Long id, String nombre, String apellido, LocalDateTime incorporacion, int legajo, double resultadoFinal, String username, boolean esAdmin
    }
}
