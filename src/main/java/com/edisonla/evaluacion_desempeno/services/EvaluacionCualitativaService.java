package com.edisonla.evaluacion_desempeno.services;

import com.edisonla.evaluacion_desempeno.dtos.EvaluacionCualitativaDto;
import com.edisonla.evaluacion_desempeno.entities.EvaluacionCualitativa;
import com.edisonla.evaluacion_desempeno.mappers.EvaluacionCualitativaMapper;
import com.edisonla.evaluacion_desempeno.repositories.EvaluacionCualitativaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class EvaluacionCualitativaService {

    @Autowired
    private EvaluacionCualitativaRepository evaluacionCualitativaRepository;

    @Autowired
    private EvaluacionCualitativaMapper evaluacionCualitativaMapper;

    public Iterable<EvaluacionCualitativaDto> getAll() {
        return evaluacionCualitativaRepository.findAll()
                .stream()
                .map(evaluacionCualitativaMapper::toDto) //method reference reemplazo de (evaluacionCualitativa -> evaluacionCualitativaMapper.toDto(evaluacionCualitativa))
                .toList();
    }

    public EvaluacionCualitativa get(Long id) {
        return evaluacionCualitativaRepository.findById(id).orElse(null);
    }

}
