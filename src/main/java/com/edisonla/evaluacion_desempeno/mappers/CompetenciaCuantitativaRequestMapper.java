package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCuantitativaDto;
import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCuantitativaRequest;
import com.edisonla.evaluacion_desempeno.entities.CompetenciaCuantitativa;

public class CompetenciaCuantitativaRequestMapper {
    private CompetenciaCuantitativaRequestMapper() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

    public static CompetenciaCuantitativa toEntity(final CompetenciaCuantitativaRequest dto) {
        return new CompetenciaCuantitativa(dto.nombre(), dto.descripcion(), dto.fecha(), dto.resultado(), dto.validado());
    }

    public static CompetenciaCuantitativaRequest toDto(final CompetenciaCuantitativa entity) {
        return new CompetenciaCuantitativaRequest(
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getFecha(),
                entity.getResultado(),
                entity.isValidado()
        );
    }
}
