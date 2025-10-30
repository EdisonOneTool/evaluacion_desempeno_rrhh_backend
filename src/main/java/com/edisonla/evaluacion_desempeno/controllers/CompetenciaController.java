package com.edisonla.evaluacion_desempeno.controllers;

import com.edisonla.evaluacion_desempeno.dtos.CompetenciaDto;
import com.edisonla.evaluacion_desempeno.entities.Competencia;
import com.edisonla.evaluacion_desempeno.mappers.CompetenciaMapper;
import com.edisonla.evaluacion_desempeno.services.CompetenciaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/competencia")
public class CompetenciaController {

    @Autowired
    private CompetenciaService competenciaService;

    @Autowired
    private CompetenciaMapper competenciaMapper;

    @GetMapping
    public Iterable<CompetenciaDto> getAll() {
        return competenciaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetenciaDto> get(@PathVariable long id) {
        Competencia competencia = competenciaService.get(id);
        if (competencia == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(competenciaMapper.toDto(competencia));
        }
    }


}
