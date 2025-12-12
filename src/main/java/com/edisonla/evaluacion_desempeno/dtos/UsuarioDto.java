package com.edisonla.evaluacion_desempeno.dtos;

import com.edisonla.evaluacion_desempeno.enums.Roles;

import java.time.LocalDate;

public record UsuarioDto(Long id,
                        String username,
                        String nombre,
                        String apellido,
                        String email,
                        Roles roles,
                        LocalDate incorporacion,
                        int legajo,
                        String cuil) {
}
