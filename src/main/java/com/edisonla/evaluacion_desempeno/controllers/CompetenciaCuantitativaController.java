package com.edisonla.evaluacion_desempeno.controllers;

import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCuantitativaDto;
import com.edisonla.evaluacion_desempeno.entities.CompetenciaCuantitativa;
import com.edisonla.evaluacion_desempeno.services.CompetenciaCuantitativaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/competencia/cuantitativa")
public class CompetenciaCuantitativaController {
    @Autowired
    CompetenciaCuantitativaService service;

    @Autowired
    CompetenciaCuantitativaMapper mapper;

    private static final String urlBase = "/api/competencia/cuantitativa";

    @GetMapping
    public Iterable<CompetenciaCuantitativaDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetenciaCuantitativaDto> get(@PathVariable(name = "id") Long id) {
        CompetenciaCuantitativa competenciaCuantitativa =  service.get(id);
        if (competenciaCuantitativa == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(mapper.toDto(competenciaCuantitativa));
        }
    }

    @PostMapping
    public ResponseEntity<CompetenciaCuantitativaDto> create(@RequestBody CompetenciaCuantitativaDto request,
                                                    UriComponentsBuilder uriBuilder) {
        CompetenciaCuantitativaDto dto = service.create(request);
        URI location = uriBuilder.path(urlBase + "/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(location).body(dto);

    }

    @PutMapping ("/{id}")
    public ResponseEntity<CompetenciaCuantitativaDto> update(@PathVariable Long id,
                                                             @RequestBody CompetenciaCuantitativaDto request) {
        CompetenciaCuantitativaDto dto = service.update(id, request);
        if(dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @DeleteMapping ("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        CompetenciaCuantitativaDto dto = service.delete(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
