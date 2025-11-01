package com.edisonla.evaluacion_desempeno.controllers;


import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCualitativaDto;
import com.edisonla.evaluacion_desempeno.entities.CompetenciaCualitativa;
import com.edisonla.evaluacion_desempeno.mappers.CompetenciaCualitativaMapper;
import com.edisonla.evaluacion_desempeno.services.CompetenciaCualitativaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/competencia/cualitativa")
public class CompetenciaCualitativaController {

    @Autowired
    CompetenciaCualitativaService competenciaCualitativaService;

    @Autowired
    CompetenciaCualitativaMapper competenciaCualitativaMapper;

    @GetMapping
    public Iterable<CompetenciaCualitativaDto> getAll() {
        return competenciaCualitativaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetenciaCualitativaDto> get(@PathVariable(name = "id") Long id) {
        CompetenciaCualitativa competenciaCualitativa =  competenciaCualitativaService.get(id);
        if (competenciaCualitativa == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(competenciaCualitativaMapper.toDto(competenciaCualitativa));
        }
    }

}
