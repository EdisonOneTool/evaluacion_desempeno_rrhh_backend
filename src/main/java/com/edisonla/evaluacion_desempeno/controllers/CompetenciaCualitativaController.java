package com.edisonla.evaluacion_desempeno.controllers;


import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCualitativaDto;
import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCuantitativaDto;
import com.edisonla.evaluacion_desempeno.entities.CompetenciaCualitativa;
import com.edisonla.evaluacion_desempeno.mappers.CompetenciaCualitativaMapper;
import com.edisonla.evaluacion_desempeno.services.CompetenciaCualitativaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/competencia/cualitativa")
public class CompetenciaCualitativaController {

    @Autowired
    CompetenciaCualitativaService service;

    @Autowired
    CompetenciaCualitativaMapper mapper;

    private static final String urlBase = "/api/competencia/cualitativa";

    @GetMapping
    public Iterable<CompetenciaCualitativaDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetenciaCualitativaDto> get(@PathVariable(name = "id") Long id) {
        CompetenciaCualitativa competenciaCualitativa =  service.get(id);
        if (competenciaCualitativa == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(mapper.toDto(competenciaCualitativa));
        }
    }

    @PostMapping
    public ResponseEntity<CompetenciaCualitativaDto> create(@RequestBody CompetenciaCualitativaDto request,
                                                             UriComponentsBuilder uriBuilder) {
        CompetenciaCualitativaDto dto = service.create(request);
        URI location = uriBuilder.path(urlBase + "/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(location).body(dto);

    }

    @PutMapping ("/{id}")
    public ResponseEntity<CompetenciaCualitativaDto> update(@PathVariable Long id,
                                                             @RequestBody CompetenciaCualitativaDto request) {
        CompetenciaCualitativaDto dto = service.update(id, request);
        if(dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @DeleteMapping ("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        CompetenciaCualitativaDto dto = service.delete(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
