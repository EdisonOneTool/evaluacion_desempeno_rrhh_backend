package com.edisonla.evaluacion_desempeno.services;

import com.edisonla.evaluacion_desempeno.dtos.EvaluacionCuantitativaDto;
import com.edisonla.evaluacion_desempeno.entities.EvaluacionCuantitativa;
import com.edisonla.evaluacion_desempeno.mappers.EvaluacionCuantitativaMapper;
import com.edisonla.evaluacion_desempeno.repositories.EvaluacionCuantitativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluacionCuantitativaService {

    @Autowired
    private EvaluacionCuantitativaRepository evaluacionCuantitativaRepository;

    @Autowired
    private EvaluacionCuantitativaMapper evaluacionCuantitativaMapper;

    public Iterable<EvaluacionCuantitativaDto> getAll() {
        return evaluacionCuantitativaRepository.findAll()
                .stream()
                .map(evaluacionCuantitativaMapper::toDto) //method reference reemplazo de (evaluacionCualitativa -> evaluacionCualitativaMapper.toDto(evaluacionCualitativa))
                .toList();
    }

    public EvaluacionCuantitativa get(Long id) {
        return evaluacionCuantitativaRepository.findById(id).orElse(null);
    }

}
