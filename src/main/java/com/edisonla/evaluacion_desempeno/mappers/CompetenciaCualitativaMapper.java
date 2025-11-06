package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCualitativaDto;
import com.edisonla.evaluacion_desempeno.dtos.ComportamientoDto;
import com.edisonla.evaluacion_desempeno.entities.CompetenciaCualitativa;
import com.edisonla.evaluacion_desempeno.entities.Comportamiento;

public class CompetenciaCualitativaMapper {
    private CompetenciaCualitativaMapper() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

    public static CompetenciaCualitativa toEntity(final CompetenciaCualitativaDto dto) {
        return new CompetenciaCualitativa(dto.id(), dto.nombre(), dto.descripcion(), dto.devolucion(), dto.validado());
    }

    public static CompetenciaCualitativaDto toDto(final CompetenciaCualitativa entity) {
        return new CompetenciaCualitativaDto(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getDevolucion(),
                entity.isValidado()
        );
    }

    //Long id, String nombre, String descripcion, LocalDate fecha
}
