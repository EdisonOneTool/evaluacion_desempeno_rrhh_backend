package com.edisonla.evaluacion_desempeno.mappers;

import com.edisonla.evaluacion_desempeno.dtos.EvaluadoDto;
import com.edisonla.evaluacion_desempeno.entities.Evaluado;

public class EvaluadoMapper {
    private EvaluadoMapper() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }


    public static EvaluadoDto toDto(Evaluado e) {
        return new EvaluadoDto(
                e.getId(),
                e.getDni(),
                e.getCuil(),
                e.getNombre(),
                e.getApellido(),
                e.getIncorporacion(),
                e.getLegajo(),
                e.getSeniority(),
                e.getDisponibilidad(),
                e.getMail(),
                e.getCelula(),
                e.getEquipoMetodologico(),
                e.getNuevoPuesto(),
                e.getPuesto(),
                e.getResultadoFinal(),
                e.isAdmin()
        );
    }

    public static Evaluado toEntity(EvaluadoDto dto) {
        Evaluado e = new Evaluado();
        e.setDni(dto.dni());
        e.setCuil(dto.cuil());
        e.setNombre(dto.nombre());
        e.setApellido(dto.apellido());
        e.setIncorporacion(dto.incorporacion());
        e.setLegajo(dto.legajo());
        e.setSeniority(dto.seniority());
        e.setDisponibilidad(dto.disponibilidad());
        e.setMail(dto.mail());
        e.setCelula(dto.celula());
        e.setEquipoMetodologico(dto.equipoMetodologico());
        e.setNuevoPuesto(dto.nuevoPuesto());
        e.setPuesto(dto.puesto());
        e.setResultadoFinal(dto.resultadoFinal());
        e.setAdmin(dto.esAdmin());
        return e;
    }
        //Long id, String nombre, String apellido, LocalDateTime incorporacion, int legajo, double resultadoFinal, String username, String mail, String password, boolean esAdmin
}

