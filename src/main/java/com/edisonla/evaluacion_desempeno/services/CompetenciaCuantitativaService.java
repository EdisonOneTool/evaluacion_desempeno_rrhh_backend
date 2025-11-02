package com.edisonla.evaluacion_desempeno.services;

import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCuantitativaDto;
import com.edisonla.evaluacion_desempeno.dtos.ComportamientoDto;
import com.edisonla.evaluacion_desempeno.entities.CompetenciaCuantitativa;
import com.edisonla.evaluacion_desempeno.entities.Comportamiento;
import com.edisonla.evaluacion_desempeno.mappers.CompetenciaCuantitativaMapper;
import com.edisonla.evaluacion_desempeno.repositories.CompetenciaCuantitativaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompetenciaCuantitativaService {

    private final CompetenciaCuantitativaRepository repository;

    private final CompetenciaCuantitativaMapper mapper;

    public Iterable<CompetenciaCuantitativaDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto) //method reference reemplazo de (evaluacionCualitativa -> evaluacionCualitativaMapper.toDto(evaluacionCualitativa))
                .toList();
    }

    public CompetenciaCuantitativa get(Long id) {
        return repository.findById(id).orElse(null);
    }

    public CompetenciaCuantitativaDto create(CompetenciaCuantitativaDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public CompetenciaCuantitativaDto update(Long id, CompetenciaCuantitativaDto dto) {
        CompetenciaCuantitativa cc = repository.findById(id).orElse(null);
        if(cc == null) {
            return null;
        } else {
            repository.save(cc);
            return mapper.toDto(cc);
        }
    }

    public CompetenciaCuantitativaDto delete(Long id) {
        CompetenciaCuantitativa cc = repository.findById(id).orElse(null);
        if (cc == null) {
            return null;
        } else {
            repository.delete(cc);
            return mapper.toDto(cc);
        }
    }

}
