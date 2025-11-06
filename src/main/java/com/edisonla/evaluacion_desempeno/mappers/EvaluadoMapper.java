package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.EvaluadoDto;
import com.edisonla.evaluacion_desempeno.entities.Evaluado;

public class EvaluadoMapper {
    private EvaluadoMapper() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

    public static Evaluado toEntity(final EvaluadoDto dto) {
        return new Evaluado(dto.nombre(), dto.apellido(), dto.incorporacion(), dto.legajo(), dto.resultadoFinal(), dto.username(), dto.esAdmin());
    }

    public static EvaluadoDto toDto(final Evaluado evlauado) {
        return new EvaluadoDto(
                evlauado.getId(),
                evlauado.getNombre(),
                evlauado.getApellido(),
                evlauado.getIncorporacion(),
                evlauado.getLegajo(),
                evlauado.getResultadoFinal(),
                evlauado.getUsername(),
                evlauado.isEsAdmin()
        );
        //Long id, String nombre, String apellido, LocalDateTime incorporacion, int legajo, double resultadoFinal, String username, boolean esAdmin
    }
}
