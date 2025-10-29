package com.edisonla.evaluacion_desempeno.repositories;

import com.edisonla.evaluacion_desempeno.entities.Evaluado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluadoRepository extends JpaRepository<Evaluado, Long> {
}
