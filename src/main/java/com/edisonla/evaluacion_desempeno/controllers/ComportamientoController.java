package com.edisonla.evaluacion_desempeno.controllers;

import com.edisonla.evaluacion_desempeno.dtos.ComportamientoDto;
import com.edisonla.evaluacion_desempeno.entities.Comportamiento;
import com.edisonla.evaluacion_desempeno.mappers.ComportamientoMapper;
import com.edisonla.evaluacion_desempeno.services.ComportamientoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comportamiento")
public class ComportamientoController {

    @Autowired
    private ComportamientoService comportamientoService;

    @Autowired
    private ComportamientoMapper comportamientoMapper;

    @GetMapping
    public Iterable<ComportamientoDto> getAll() {
        return comportamientoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComportamientoDto> get(@PathVariable long id) {
        Comportamiento comportamiento = comportamientoService.get(id);
        if (comportamiento == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(comportamientoMapper.toDto(comportamiento));
        }
    }


}
