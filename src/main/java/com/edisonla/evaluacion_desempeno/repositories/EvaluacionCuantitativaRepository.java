package com.edisonla.evaluacion_desempeno.repositories;

import com.edisonla.evaluacion_desempeno.entities.EvaluacionCuantitativa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EvaluacionCuantitativaRepository extends JpaRepository<EvaluacionCuantitativa, Long> {
    EvaluacionCuantitativa findByNombre(String nombre);
    List<EvaluacionCuantitativa> findAllByFecha(LocalDateTime fecha);
}
