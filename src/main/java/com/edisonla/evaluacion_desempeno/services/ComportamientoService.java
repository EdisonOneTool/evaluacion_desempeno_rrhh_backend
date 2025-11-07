package com.edisonla.evaluacion_desempeno.services;

import com.edisonla.evaluacion_desempeno.dtos.ComportamientoDto;
import com.edisonla.evaluacion_desempeno.dtos.ComportamientoRequest;
import com.edisonla.evaluacion_desempeno.entities.Comportamiento;
import com.edisonla.evaluacion_desempeno.mappers.ComportamientoMapper;
import com.edisonla.evaluacion_desempeno.mappers.ComportamientoRequestMapper;
import com.edisonla.evaluacion_desempeno.mappers.EvaluadoMapper;
import com.edisonla.evaluacion_desempeno.repositories.ComportamientoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ComportamientoService {

    private final ComportamientoRepository repository;

    public Iterable<ComportamientoDto> getAll() {
        return repository.findAll()
                .stream()
                .map(ComportamientoMapper::toDto)
                .toList();
    }

    public Comportamiento get(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ComportamientoRequest create(ComportamientoRequest dto) {
        repository.save(ComportamientoRequestMapper.toEntity(dto));
        //return ComportamientoRequestMapper.toDto(repository.findByNombre(dto.nombre()));
        return null;
    }

    public ComportamientoDto update(Long id, ComportamientoDto dto) {
        Comportamiento comportamiento = repository.findById(id).orElse(null);
        if(comportamiento == null) {
            return null;
        } else {
            repository.save(comportamiento);
            return ComportamientoMapper.toDto(comportamiento);
        }
    }

    public ComportamientoDto delete(Long id) {
        Comportamiento comportamiento = repository.findById(id).orElse(null);
        if (comportamiento == null) {
            return null;
        } else {
            repository.delete(comportamiento);
            return ComportamientoMapper.toDto(comportamiento);
        }
    }
}
