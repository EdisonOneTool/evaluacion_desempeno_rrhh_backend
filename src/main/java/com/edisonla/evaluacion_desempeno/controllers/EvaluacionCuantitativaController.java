package com.edisonla.evaluacion_desempeno.controllers;

import com.edisonla.evaluacion_desempeno.dtos.EvaluacionCuantitativaDto;
import com.edisonla.evaluacion_desempeno.entities.EvaluacionCuantitativa;
import com.edisonla.evaluacion_desempeno.mappers.EvaluacionCuantitativaMapper;
import com.edisonla.evaluacion_desempeno.services.EvaluacionCuantitativaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/eval/cuan")
public class EvaluacionCuantitativaController {
    @Autowired
    EvaluacionCuantitativaService evaluacionCuantitativaService;

    @Autowired
    EvaluacionCuantitativaMapper evaluacionCuantitativaMapper;

    @GetMapping
    public Iterable<EvaluacionCuantitativaDto> getAll() {
        return evaluacionCuantitativaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionCuantitativaDto> get(@PathVariable(name = "id") Long id) {
        EvaluacionCuantitativa evaluacionCuantitativa =  evaluacionCuantitativaService.get(id);
        if (evaluacionCuantitativa == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(evaluacionCuantitativaMapper.toDto(evaluacionCuantitativa));
        }
    }
}
