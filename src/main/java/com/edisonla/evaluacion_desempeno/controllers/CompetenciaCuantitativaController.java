package com.edisonla.evaluacion_desempeno.controllers;

import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCuantitativaDto;
import com.edisonla.evaluacion_desempeno.entities.CompetenciaCuantitativa;
import com.edisonla.evaluacion_desempeno.mappers.CompetenciaCuantitativaMapper;
import com.edisonla.evaluacion_desempeno.services.CompetenciaCuantitativaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/competencia/cuantitativa")
public class CompetenciaCuantitativaController {
    @Autowired
    CompetenciaCuantitativaService competenciaCuantitativaService;

    @Autowired
    CompetenciaCuantitativaMapper competenciaCuantitativaMapper;

    @GetMapping
    public Iterable<CompetenciaCuantitativaDto> getAll() {
        return competenciaCuantitativaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetenciaCuantitativaDto> get(@PathVariable(name = "id") Long id) {
        CompetenciaCuantitativa competenciaCuantitativa =  competenciaCuantitativaService.get(id);
        if (competenciaCuantitativa == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(competenciaCuantitativaMapper.toDto(competenciaCuantitativa));
        }
    }
}
