package com.edisonla.evaluacion_desempeno.repositories;

import com.edisonla.evaluacion_desempeno.entities.Evaluado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EvaluadoRepository extends JpaRepository<Evaluado, Long> {
    List<Evaluado> findAllByIncorporacion(LocalDateTime fecha);
    Evaluado findByLegajo(int legajo);
    Evaluado findByNombre(String nombre);
    Evaluado findByApellido(String apellido);
    Evaluado findByMail(String mail);
    Evaluado findByUsername(String username);
}
