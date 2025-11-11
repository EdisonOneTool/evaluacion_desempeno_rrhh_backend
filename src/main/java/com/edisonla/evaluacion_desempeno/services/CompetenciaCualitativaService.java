package com.edisonla.evaluacion_desempeno.services;

import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCualitativaDto;
import com.edisonla.evaluacion_desempeno.dtos.CompetenciaCualitativaRequest;
import com.edisonla.evaluacion_desempeno.entities.CompetenciaCualitativa;
import com.edisonla.evaluacion_desempeno.entities.Evaluado;
import com.edisonla.evaluacion_desempeno.mappers.CompetenciaCualitativaMapper;
import com.edisonla.evaluacion_desempeno.mappers.CompetenciaCualitativaRequestMapper;
import com.edisonla.evaluacion_desempeno.repositories.CompetenciaCualitativaRepository;
import com.edisonla.evaluacion_desempeno.repositories.EvaluadoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CompetenciaCualitativaService {

    private final CompetenciaCualitativaRepository repository;
    private final EvaluadoRepository evaluadoRepository;

    @Autowired
    private final CompetenciaCualitativaMapper ccMapper;
    @Autowired
    private final CompetenciaCualitativaRequestMapper ccRequestMapper;

    public Iterable<CompetenciaCualitativaDto> getAll(Long evaluadoId) {
        return repository.findAll()
                .stream()
                .filter(cc -> cc.getEvaluador().getId().equals(evaluadoId)) // Reemplazar por filtrar desde repo
                .map(ccMapper::toDto) //method reference reemplazo de (evaluacionCualitativa -> evaluacionCualitativaMapper.toDto(evaluacionCualitativa))
                .toList();
    }

    public CompetenciaCualitativa get(Long evaluadoId, Long id) {
        CompetenciaCualitativa cc = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el elemento con id: " + id));
        if(!cc.getEvaluador().getId().equals(evaluadoId)) {
            throw new IllegalArgumentException("Competencia Cualitativa " + id + " no pertenece al Evaluado " + evaluadoId);
        } else {
            return cc;
        }
    }

    @Transactional
    public CompetenciaCualitativaDto create(Long evaluadoId, CompetenciaCualitativaRequest dto) {
        Evaluado evaluado = evaluadoRepository.findById(evaluadoId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el elemento con id: " + evaluadoId));

        CompetenciaCualitativa entidad = ccRequestMapper.toEntity(dto);
        evaluado.addCompetenciaCualitativa(entidad); // Establece la relación bidireccional
        CompetenciaCualitativa res = repository.save(entidad);
        return ccMapper.toDto(res);
    }

    @Transactional
    public CompetenciaCualitativaRequest update(Long evaluadoId, Long id, CompetenciaCualitativaRequest dto) {
        CompetenciaCualitativa cc = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el elemento con id: " + id));
        if(!cc.getEvaluador().getId().equals(evaluadoId)) {
            throw new IllegalArgumentException("Competencia Cualitativa " + id + " no pertenece al Evaluado " + evaluadoId);
        } else {
            CompetenciaCualitativa updated = ccRequestMapper.toEntity(dto);
            updated.setId(cc.getId());
            CompetenciaCualitativa res = repository.save(updated);
            return ccRequestMapper.toDto(res);
        }
    }

    @Transactional
    public void delete(Long evaluadoId, Long id) {
        CompetenciaCualitativa cc = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el elemento con id: " + id));
        if(!cc.getEvaluador().getId().equals(evaluadoId)) {
            throw new IllegalArgumentException("Competencia Cualitativa " + id + " no pertenece al Evaluado " + evaluadoId);
        } else {
            repository.delete(cc);
        }
    }
}
