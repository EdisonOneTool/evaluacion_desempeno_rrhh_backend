package com.edisonla.evaluacion_desempeno.services;

import com.edisonla.evaluacion_desempeno.dtos.CompetenciaDto;
import com.edisonla.evaluacion_desempeno.entities.Competencia;
import com.edisonla.evaluacion_desempeno.mappers.CompetenciaMapper;
import com.edisonla.evaluacion_desempeno.repositories.CompetenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetenciaService {

    @Autowired
    private CompetenciaRepository competenciaRepository;

    @Autowired
    private CompetenciaMapper competenciaMapper;

    public Iterable<CompetenciaDto> getAllCompetencias() {
        return competenciaRepository.findAll()
                .stream()
                .map(competenciaMapper::toDto)
                .toList();
    }

    public Competencia getCompetencia(Long id) {
        return competenciaRepository.findById(id).orElse(null);
    }
}
