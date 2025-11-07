package com.edisonla.evaluacion_desempeno.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "competencia_cuantitativa")
public class CompetenciaCuantitativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "resultado")
    private double resultado;

    @Column (name= "validado")
    private boolean validado;

    @OneToMany(mappedBy = "competenciaCuantitativa", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Comportamiento> comportamientos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "evaluado_id")
    @ToString.Exclude
    private Evaluado evaluador;


    public CompetenciaCuantitativa(Long id, String nombre, String descripcion, Date fecha, double resultado, boolean validado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.resultado = resultado;
        this.validado = validado;
    }

    public CompetenciaCuantitativa(String nombre, String descripcion, Date fecha, double resultado, boolean validado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.resultado = resultado;
        this.validado = validado;
    }

    public void addComportamiento(Comportamiento comportamiento) {
        this.comportamientos.add(comportamiento);
        comportamiento.setCompetenciaCuantitativa(this);
    }
}
