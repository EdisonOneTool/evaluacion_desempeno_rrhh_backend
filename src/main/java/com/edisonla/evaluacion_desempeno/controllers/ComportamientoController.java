package com.edisonla.evaluacion_desempeno.controllers;

import com.edisonla.evaluacion_desempeno.dtos.ComportamientoDto;
import com.edisonla.evaluacion_desempeno.dtos.ComportamientoRequest;
import com.edisonla.evaluacion_desempeno.entities.Comportamiento;
import com.edisonla.evaluacion_desempeno.mappers.ComportamientoMapper;
import com.edisonla.evaluacion_desempeno.services.ComportamientoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comportamiento")
public class ComportamientoController {

    @Autowired
    private ComportamientoService service;

    private static final String urlBase = "/api/comportamiento";

    @GetMapping
    public Iterable<ComportamientoDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComportamientoDto> get(@PathVariable long id) {
        Comportamiento comportamiento = service.get(id);
        if (comportamiento == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(ComportamientoMapper.toDto(comportamiento));
        }
    }

    @PostMapping
    public ResponseEntity<ComportamientoRequest> create(@RequestBody ComportamientoRequest request,
                                              UriComponentsBuilder uriBuilder) {
        ComportamientoRequest dto = service.create(request);
        URI location = uriBuilder.path(urlBase + "/{id}").buildAndExpand(123).toUri();
        return ResponseEntity.created(location).body(dto);

    }

    @PutMapping ("/{id}")
    public ResponseEntity<ComportamientoDto> update(@PathVariable Long id, @RequestBody ComportamientoDto request) {
        ComportamientoDto dto = service.update(id, request);
        if(dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ComportamientoDto dto = service.delete(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
