package com.edisonla.evaluacion_desempeno.services;

import com.edisonla.evaluacion_desempeno.dtos.EvaluadoDto;
import com.edisonla.evaluacion_desempeno.entities.Evaluado;
import com.edisonla.evaluacion_desempeno.mappers.EvaluadoMapper;
import com.edisonla.evaluacion_desempeno.repositories.EvaluadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluadoService {

    @Autowired
    private EvaluadoRepository evaluadoRepository;

    @Autowired
    private EvaluadoMapper evaluadoMapper;

    public Iterable<EvaluadoDto> getAll() {
        return evaluadoRepository.findAll()
                .stream()
                .map(evaluadoMapper::toDto) //method reference reemplazo de (evaluacionCualitativa -> evaluacionCualitativaMapper.toDto(evaluacionCualitativa))
                .toList();
    }

    public Evaluado get(Long id) {
        return evaluadoRepository.findById(id).orElse(null);
    }
}
