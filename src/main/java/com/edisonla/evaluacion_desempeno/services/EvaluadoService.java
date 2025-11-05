package com.edisonla.evaluacion_desempeno.services;

import com.edisonla.evaluacion_desempeno.dtos.EvaluadoDto;
import com.edisonla.evaluacion_desempeno.entities.Evaluado;
import com.edisonla.evaluacion_desempeno.mappers.EvaluadoMapper;
import com.edisonla.evaluacion_desempeno.repositories.EvaluadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EvaluadoService {

    private final EvaluadoRepository repository;

    private final EvaluadoMapper mapper;

    public Iterable<EvaluadoDto> getAll() {
        List<Evaluado> e = repository.findAll();
        return e
                .stream()
                .map(mapper::toDto) //method reference reemplazo de (evaluacionCualitativa -> evaluacionCualitativaMapper.toDto(evaluacionCualitativa))
                .toList();
    }

    public EvaluadoDto get(Long id) {
        Evaluado e = repository.findById(id).orElse(null);
        return mapper.toDto(e);
    }

    public EvaluadoDto create(EvaluadoDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public EvaluadoDto update(Long id, EvaluadoDto dto) {
        Evaluado evaluado = repository.findById(id).orElse(null);
        if(evaluado == null) {
            return null;
        } else {
            repository.save(evaluado);
            return mapper.toDto(evaluado);
        }
    }

    public EvaluadoDto delete(Long id) {
        Evaluado evaluado = repository.findById(id).orElse(null);
        if (evaluado == null) {
            return null;
        } else {
            repository.delete(evaluado);
            return mapper.toDto(evaluado);
        }
    }
}
