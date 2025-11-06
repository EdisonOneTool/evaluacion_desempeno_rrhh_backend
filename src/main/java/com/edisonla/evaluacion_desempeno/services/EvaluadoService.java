package com.edisonla.evaluacion_desempeno.services;

import com.edisonla.evaluacion_desempeno.dtos.EvaluadoDto;
import com.edisonla.evaluacion_desempeno.entities.Evaluado;
import com.edisonla.evaluacion_desempeno.mappers.EvaluadoMapper;
import com.edisonla.evaluacion_desempeno.repositories.EvaluadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EvaluadoService {

    private final EvaluadoRepository repository;

    // private final EvaluadoMapper mapper;

    public Iterable<EvaluadoDto> getAll() {
        List<Evaluado> e = repository.findAll();
        return e
                .stream()
                .map(EvaluadoMapper::toDto) //method reference reemplazo de (e -> mapper.toDto(e))
                .toList();
    }

    public EvaluadoDto get(Long id) {
        Evaluado e = repository.findById(id).orElse(null);
        if (e == null) { return null; }
        return EvaluadoMapper.toDto(e);
    }

    public EvaluadoDto create(EvaluadoDto dto) {
        return EvaluadoMapper.toDto(repository.save(EvaluadoMapper.toEntity(dto)));
    }

    public EvaluadoDto update(Long id, EvaluadoDto dto) {
        Evaluado evaluado = repository.findById(id).orElse(null);
        if(evaluado == null) {
            return null;
        } else {
            repository.save(evaluado);
            return EvaluadoMapper.toDto(evaluado);
        }
    }

    public EvaluadoDto delete(Long id) {
        Evaluado evaluado = repository.findById(id).orElse(null);
        if (evaluado == null) {
            return null;
        } else {
            repository.delete(evaluado);
            return EvaluadoMapper.toDto(evaluado);
        }
    }
}
