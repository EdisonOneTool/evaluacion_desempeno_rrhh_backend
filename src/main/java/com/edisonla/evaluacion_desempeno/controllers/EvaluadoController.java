package com.edisonla.evaluacion_desempeno.controllers;

import com.edisonla.evaluacion_desempeno.dtos.EvaluadoDto;
import com.edisonla.evaluacion_desempeno.dtos.EvaluadoRequest;
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
    public ResponseEntity<EvaluadoDto> create(@RequestBody EvaluadoRequest request,
                                              UriComponentsBuilder uriBuilder) {
        EvaluadoDto dto = service.create(request);
        URI location = uriBuilder.path(urlBase + "/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(location).body(dto);

    }

    @PutMapping ("/{id}")
    public ResponseEntity<EvaluadoRequest> update(@PathVariable Long id, @RequestBody EvaluadoRequest request) {
        EvaluadoRequest dto = service.update(id, request);
        if(dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean isDeleted = service.delete(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
