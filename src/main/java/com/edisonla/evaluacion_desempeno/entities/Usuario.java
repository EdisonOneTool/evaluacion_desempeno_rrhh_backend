package com.edisonla.evaluacion_desempeno.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "nombre")
    private String nombre;

    @Column (name = "apellido")
    private String apellido;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String roles;

    @Column(name = "incorporacion")
    private Date incorporacion;

    @Column(name = "legajo", unique = true, nullable = false)
    private int legajo;

    @Column(name = "cuil", unique = true, nullable = false)
    private String cuil;

    @Column (name = "puesto")
    private String puesto;

    @Column (name = "celula")
    private String celula;

    // Todas las evaluaciones donde este usuario es el evaluador
    @OneToMany(mappedBy = "evaluador", fetch = FetchType.LAZY)
    private List<Evaluacion> evaluacionesAjenas = new ArrayList<>();

    // Todas las evaluaciones donde este usuario es el validador
    @OneToMany(mappedBy = "validador", fetch = FetchType.LAZY)
    private List<Evaluacion> validaciones = new ArrayList<>();

    // Todas las evaluaciones donde este usuario es el evaluado
    @OneToMany(mappedBy = "evaluado", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Evaluacion> evaluacionesPropias = new ArrayList<>();
}
