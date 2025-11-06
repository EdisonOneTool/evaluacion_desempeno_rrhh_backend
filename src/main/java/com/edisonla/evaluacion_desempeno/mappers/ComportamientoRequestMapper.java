package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.ComportamientoRequest;
import com.edisonla.evaluacion_desempeno.entities.Comportamiento;

public class ComportamientoRequestMapper {
    private ComportamientoRequestMapper() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

    public static Comportamiento toEntity(final ComportamientoRequest dto) {
        return new Comportamiento(dto.nombre(), dto.descripcion(), dto.calificacion());
    }

    public static ComportamientoRequest toDto(final Comportamiento entity) {
        return new ComportamientoRequest(
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getCalificacion()
        );
        //Long id, String nombre, String descripcion, double calificacion
    }
}

