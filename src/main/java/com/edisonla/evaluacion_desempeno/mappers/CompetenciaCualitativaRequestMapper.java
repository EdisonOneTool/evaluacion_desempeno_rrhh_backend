package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCualitativaDto;
import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCualitativaRequest;
import com.edisonla.evaluacion_desempeno.entities.CompetenciaCualitativa;

public class CompetenciaCualitativaRequestMapper {
    private CompetenciaCualitativaRequestMapper() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

    public static CompetenciaCualitativa toEntity(final CompetenciaCualitativaRequest dto) {
        return new CompetenciaCualitativa(dto.nombre(), dto.descripcion(), dto.devolucion(), dto.validado());
    }

    public static CompetenciaCualitativaRequest toDto(final CompetenciaCualitativa entity) {
        return new CompetenciaCualitativaRequest(
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getDevolucion(),
                entity.isValidado()
        );
    }

    //Long id, String nombre, String descripcion, LocalDate fecha
}
