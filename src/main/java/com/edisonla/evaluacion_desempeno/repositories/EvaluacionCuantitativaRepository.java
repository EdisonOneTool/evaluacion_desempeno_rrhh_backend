package com.edisonla.evaluacion_desempeno.repositories;

import com.edisonla.evaluacion_desempeno.entities.EvaluacionCualitativa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluacionCuantitativaRepository extends JpaRepository<EvaluacionCualitativa, Long> {
}
