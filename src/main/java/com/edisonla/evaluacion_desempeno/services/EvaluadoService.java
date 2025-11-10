package com.edisonla.evaluacion_desempeno.services;

import com.edisonla.evaluacion_desempeno.dtos.EvaluadoDto;
import com.edisonla.evaluacion_desempeno.dtos.EvaluadoRequest;
import com.edisonla.evaluacion_desempeno.entities.Evaluado;
import com.edisonla.evaluacion_desempeno.mappers.EvaluadoMapper;
import com.edisonla.evaluacion_desempeno.mappers.EvaluadoRequestMapper;
import com.edisonla.evaluacion_desempeno.repositories.EvaluadoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class EvaluadoService {

    private final EvaluadoRepository repository;

    public Iterable<EvaluadoDto> getAll() {
        List<Evaluado> e = repository.findAll();
        return e
                .stream()
                .map(EvaluadoMapper::toDto) //method reference reemplazo de (e -> mapper.toDto(e))
                .toList();
    }

    public EvaluadoDto get(Long id) {
        Evaluado e = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el elemento con id: " + id));
        return EvaluadoMapper.toDto(e);
    }

    @Transactional
    public EvaluadoDto create(EvaluadoRequest dto) {
        Evaluado e = repository.save(EvaluadoRequestMapper.toEntity(dto));
        return EvaluadoMapper.toDto(e);
    }

    @Transactional
    public EvaluadoRequest update(Long id, EvaluadoRequest dto) {
        Evaluado original = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el elemento con id: " + id));
        Evaluado updated = EvaluadoRequestMapper.toEntity(dto);
        updated.setId(original.getId());
        Evaluado res = repository.save(updated);
        return EvaluadoRequestMapper.toDto(res);
    }

    @Transactional
    public void delete(Long id) {
        Evaluado evaluado = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el elemento con id: " + id));
        repository.delete(evaluado);
    }


    @Transactional
    public void addNomina(MultipartFile file) {
        List<Evaluado> evaluados = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            boolean primeraLinea = true;

            while ((line = br.readLine()) != null) {

                // Saltamos encabezado
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                // Si el separador es TAB → "\t"
                String[] datos = line.split(",");

                if (datos.length < 13) {
                    throw new RuntimeException("Fila con columnas insuficientes: " + line);
                }

                Evaluado e = new Evaluado();

                e.setLegajo(Integer.parseInt(datos[0].trim()));
                e.setCuil(datos[1].trim());
                e.setDni(datos[2].trim());
                e.setNombre(datos[3].trim());
                e.setApellido(datos[4].trim());

                // Parsear fecha (formato "yyyy-MM-dd" o "dd/MM/yyyy")
                String fechaTexto = datos[5].trim();
                Date fecha = parsearFecha(fechaTexto);
                e.setIncorporacion(fecha);

                e.setSeniority(datos[6].trim());
                e.setDisponibilidad(datos[7].trim());
                e.setMail(datos[8].trim());
                e.setCelula(datos[9].trim());
                e.setEquipoMetodologico(datos[10].trim());
                e.setNuevoPuesto(datos[11].trim());
                e.setPuesto(datos[12].trim());

                evaluados.add(e);
            }

            repository.saveAll(evaluados);

        } catch (Exception ex) {
            throw new RuntimeException("Error al procesar el CSV: " + ex.getMessage(), ex);
        }
    }


    private Date parsearFecha(String fechaTexto) {
        List<String> formatos = List.of("yyyy-MM-dd", "dd/MM/yyyy", "dd-MM-yyyy");

        for (String formato : formatos) {
            try {
                return new SimpleDateFormat(formato).parse(fechaTexto);
            } catch (Exception ignored) {
            }
        }
        throw new RuntimeException("Formato de fecha no reconocido: " + fechaTexto);
    }
}



