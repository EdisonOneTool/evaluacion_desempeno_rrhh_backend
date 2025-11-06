package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCualitativaDto;
import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCuantitativaDto;
import com.edisonla.evaluacion_desempeno.entities.CompetenciaCualitativa;
import com.edisonla.evaluacion_desempeno.entities.CompetenciaCuantitativa;

public class CompetenciaCuantitativaMapper {
    private CompetenciaCuantitativaMapper() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

    public static CompetenciaCuantitativa toEntity(final CompetenciaCuantitativaDto dto) {
        return new CompetenciaCuantitativa(dto.id(), dto.nombre(), dto.descripcion(), dto.fecha(), dto.resultado(), dto.validado());
    }

    public static CompetenciaCuantitativaDto toDto(final CompetenciaCuantitativa entity) {
        return new CompetenciaCuantitativaDto(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getFecha(),
                entity.getResultado(),
                entity.isValidado()
        );
    }
}
