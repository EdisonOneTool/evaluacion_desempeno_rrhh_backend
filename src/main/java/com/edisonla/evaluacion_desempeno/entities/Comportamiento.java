package com.edisonla.evaluacion_desempeno.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comportamiento")
public class Comportamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "calificacion")
    private double calificacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "competencia_cuantitativa_id")
    @ToString.Exclude
    private CompetenciaCuantitativa competenciaCuantitativa;


    public Comportamiento(String nombre, String descripcion, double calificacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.calificacion = calificacion;
    }
    public Comportamiento(Long id, String nombre, String descripcion, double calificacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.calificacion = calificacion;
    }
}
