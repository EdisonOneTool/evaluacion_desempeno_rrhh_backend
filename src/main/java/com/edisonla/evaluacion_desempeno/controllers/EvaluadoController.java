package com.edisonla.evaluacion_desempeno.controllers;

import com.edisonla.evaluacion_desempeno.dtos.EvaluadoDto;
import com.edisonla.evaluacion_desempeno.services.EvaluadoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/evaluados")
public class EvaluadoController {

    @Autowired
    EvaluadoService service;

    private static final String urlBase = "/api/evaluados";

    @GetMapping
    public Iterable<EvaluadoDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluadoDto> get(@PathVariable(name = "id") Long id) {
        EvaluadoDto dto =  service.get(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @PostMapping
    public ResponseEntity<EvaluadoDto> create(@RequestBody EvaluadoDto request,
                                              UriComponentsBuilder uriBuilder) {
        EvaluadoDto dto = service.create(request);
        URI location = uriBuilder.path(urlBase + "/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(location).body(dto);

    }

    @PutMapping ("/{id}")
    public ResponseEntity<EvaluadoDto> update(@PathVariable Long id, @RequestBody EvaluadoDto request) {
        EvaluadoDto dto = service.update(id, request);
        if(dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @DeleteMapping ("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        EvaluadoDto dto = service.delete(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
