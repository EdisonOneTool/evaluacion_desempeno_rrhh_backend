package com.edisonla.evaluacion_desempeno.services;

import com.edisonla.evaluacion_desempeno.dtos.ComportamientoDto;
import com.edisonla.evaluacion_desempeno.dtos.EvaluadoDto;
import com.edisonla.evaluacion_desempeno.entities.Comportamiento;
import com.edisonla.evaluacion_desempeno.entities.Evaluado;
import com.edisonla.evaluacion_desempeno.mappers.ComportamientoMapper;
import com.edisonla.evaluacion_desempeno.repositories.ComportamientoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ComportamientoService {

    private final ComportamientoRepository repository;

    private final ComportamientoMapper mapper;

    public Iterable<ComportamientoDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public Comportamiento get(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ComportamientoDto create(ComportamientoDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public ComportamientoDto update(Long id, ComportamientoDto dto) {
        Comportamiento comportamiento = repository.findById(id).orElse(null);
        if(comportamiento == null) {
            return null;
        } else {
            repository.save(comportamiento);
            return mapper.toDto(comportamiento);
        }
    }

    public ComportamientoDto delete(Long id) {
        Comportamiento comportamiento = repository.findById(id).orElse(null);
        if (comportamiento == null) {
            return null;
        } else {
            repository.delete(comportamiento);
            return mapper.toDto(comportamiento);
        }
    }
}
