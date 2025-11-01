package com.edisonla.evaluacion_desempeno.repositories;

import com.edisonla.evaluacion_desempeno.entities.CompetenciaCuantitativa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CompetenciaCuantitativaRepository extends JpaRepository<CompetenciaCuantitativa, Long> {
    CompetenciaCuantitativa findByNombre(String nombre);
    List<CompetenciaCuantitativa> findAllByFecha(LocalDateTime fecha);
}
