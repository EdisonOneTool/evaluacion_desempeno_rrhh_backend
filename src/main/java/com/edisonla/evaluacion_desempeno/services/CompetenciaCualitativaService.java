package com.edisonla.evaluacion_desempeno.services;

import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCualitativaDto;
import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCuantitativaDto;
import com.edisonla.evaluacion_desempeno.entities.CompetenciaCualitativa;
import com.edisonla.evaluacion_desempeno.entities.CompetenciaCuantitativa;
import com.edisonla.evaluacion_desempeno.mappers.CompetenciaCualitativaMapper;
import com.edisonla.evaluacion_desempeno.repositories.CompetenciaCualitativaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompetenciaCualitativaService {

    private final CompetenciaCualitativaRepository repository;

    private final CompetenciaCualitativaMapper mapper;

    public Iterable<CompetenciaCualitativaDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto) //method reference reemplazo de (evaluacionCualitativa -> evaluacionCualitativaMapper.toDto(evaluacionCualitativa))
                .toList();
    }

    public CompetenciaCualitativa get(Long id) {
        return repository.findById(id).orElse(null);
    }

    public CompetenciaCualitativaDto create(CompetenciaCualitativaDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public CompetenciaCualitativaDto update(Long id, CompetenciaCualitativaDto dto) {
        CompetenciaCualitativa cc = repository.findById(id).orElse(null);
        if(cc == null) {
            return null;
        } else {
            repository.save(cc);
            return mapper.toDto(cc);
        }
    }

    public CompetenciaCualitativaDto delete(Long id) {
        CompetenciaCualitativa cc = repository.findById(id).orElse(null);
        if (cc == null) {
            return null;
        } else {
            repository.delete(cc);
            return mapper.toDto(cc);
        }
    }
}
