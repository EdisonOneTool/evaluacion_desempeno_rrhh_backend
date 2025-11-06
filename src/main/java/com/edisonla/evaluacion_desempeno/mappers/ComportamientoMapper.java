package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.ComportamientoDto;
import com.edisonla.evaluacion_desempeno.entities.Comportamiento;

public class ComportamientoMapper {
    private ComportamientoMapper() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

    public static Comportamiento toEntity(final ComportamientoDto dto) {
        return new Comportamiento(dto.id(), dto.nombre(), dto.descripcion(), dto.calificacion());
    }

    public static ComportamientoDto toDto(final Comportamiento entity) {
        return new ComportamientoDto(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getCalificacion()
        );
        //Long id, String nombre, String descripcion, double calificacion
    }
}

