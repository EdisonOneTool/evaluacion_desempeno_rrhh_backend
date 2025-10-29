package com.edisonla.evaluacion_desempeno.controllers;


import com.edisonla.evaluacion_desempeno.dtos.EvaluacionCualitativaDto;
import com.edisonla.evaluacion_desempeno.entities.EvaluacionCualitativa;
import com.edisonla.evaluacion_desempeno.mappers.EvaluacionCualitativaMapper;
import com.edisonla.evaluacion_desempeno.services.EvaluacionCualitativaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/eval/cual")
public class EvaluacionCualitativaController {

    @Autowired
    EvaluacionCualitativaService evaluacionCualitativaService;

    @Autowired
    EvaluacionCualitativaMapper evaluacionCualitativaMapper;

    @GetMapping
    public Iterable<EvaluacionCualitativaDto> getAllEvalCual() {
        return evaluacionCualitativaService.getAllEvalCual();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionCualitativaDto> getEvalCual(@PathVariable(name = "id") Long id) {
        EvaluacionCualitativa evaluacionCualitativa =  evaluacionCualitativaService.getEvalCual(id);
        if (evaluacionCualitativa == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(evaluacionCualitativaMapper.toDto(evaluacionCualitativa));
        }
    }

}
