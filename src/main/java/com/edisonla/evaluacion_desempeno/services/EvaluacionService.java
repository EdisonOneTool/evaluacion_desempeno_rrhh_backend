package com.edisonla.evaluacion_desempeno.services;

import com.edisonla.evaluacion_desempeno.dtos.EvaluacionDto;
import com.edisonla.evaluacion_desempeno.dtos.EvaluacionesResponse;
import com.edisonla.evaluacion_desempeno.dtos.NominaEvaluacionDto;
import com.edisonla.evaluacion_desempeno.dtos.ResultadoImportacionEvaluacionDto;
import com.edisonla.evaluacion_desempeno.entities.Evaluacion;
import com.edisonla.evaluacion_desempeno.entities.Usuario;
import com.edisonla.evaluacion_desempeno.enums.EstadoEvaluacion;
import com.edisonla.evaluacion_desempeno.mappers.EvaluacionMapper;
import com.edisonla.evaluacion_desempeno.repositories.EvaluacionRepository;
import com.edisonla.evaluacion_desempeno.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.hibernate.type.descriptor.java.JdbcDateJavaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hibernate.type.descriptor.java.JdbcDateJavaType.DATE_FORMAT;

@Service
@AllArgsConstructor
public class EvaluacionService {

    private final EvaluacionRepository repository;
    private final EvaluacionMapper evaluacionMapper;
    private final UsuarioRepository userRepository;
    private final JwtService jwtService;

    @Transactional(readOnly = true)
    public Iterable<EvaluacionDto> getAll() {
        List<Evaluacion> e = repository.findAll();
        return e
                .stream()
                .map(evaluacionMapper::toDto) //method reference reemplazo de (e -> mapper.toDto(e))
                .toList();
    }

    @Transactional(readOnly = true)
    public EvaluacionDto get(Long id) {
        Evaluacion e = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el elemento con id: " + id));
        return evaluacionMapper.toDto(e);
    }

    @Transactional
    public EvaluacionDto create(EvaluacionDto dto) {
        Optional<Usuario> evaluado = userRepository.findById(dto.evaluado());
        Optional<Usuario> evaluador = userRepository.findById(dto.evaluador());
        Optional<Usuario> validador = userRepository.findById(dto.validador());

        if (evaluado.isEmpty() || evaluador.isEmpty() || validador.isEmpty()) {
            throw new IllegalArgumentException("Verificar que evaluado/evaluador/validador exista");
        }
        Evaluacion e = evaluacionMapper.toEntity(dto);
        e.setId(null);
        e.setEstado(EstadoEvaluacion.PENDIENTE);
        e.setCreado(new Date());
        e.setUltimaModificacion(new Date());
        e.setEvaluado(evaluado.get());
        e.setEvaluador(evaluador.get());
        e.setValidador(validador.get());
        return evaluacionMapper.toDto(repository.save(e));
    }

    @Transactional
    public EvaluacionDto update(Long id, EvaluacionDto dto) {
        Evaluacion original = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el elemento con id: " + id));
        if (!(userRepository.existsById(dto.evaluado()) || userRepository.existsById(dto.evaluador()) ||
                userRepository.existsById(dto.validador()))) {
            throw new IllegalArgumentException("Verificar que el evaluado/evaluador/validador exista");
        }
        Evaluacion updated = evaluacionMapper.toEntity(dto);
        updated.setId(original.getId());
        updated.setCreado(original.getCreado());
        updated.setUltimaModificacion(new Date());
        updated.setEvaluado(original.getEvaluado());
        updated.setEvaluador(original.getEvaluador());
        updated.setValidador(original.getValidador());
        Evaluacion res = repository.save(updated);
        return evaluacionMapper.toDto(res);
    }

    @Transactional
    public void delete(Long id) {
        Evaluacion evaluacion = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el elemento con id: " + id));
        repository.delete(evaluacion);
    }


    @Transactional(readOnly = true)
    public EvaluacionesResponse getEvaluacionesByToken(String token) {
        String email = jwtService.extractEmail(token);

        Usuario usuario = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con email: " + email));

        Long userId = usuario.getId();

        List<EvaluacionDto> evaluaciones = repository.findAllByEvaluadorId(userId)
                .stream()
                .map(evaluacionMapper::toDto)
                .toList();

        List<EvaluacionDto> validaciones = repository.findAllByValidadorId(userId)
                .stream()
                .map(evaluacionMapper::toDto)
                .toList();

        return new EvaluacionesResponse(evaluaciones, validaciones);
    }


  
    public List<NominaEvaluacionDto> leerEvaluacionesExcel(MultipartFile file) throws IOException {
        List<NominaEvaluacionDto> evaluaciones = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                int rowNum = i + 1; // para mensajes de error (Excel usa base 1)

                String legajoEvaluadoStr = formatter.formatCellValue(row.getCell(0));
                if (legajoEvaluadoStr == null || legajoEvaluadoStr.isBlank()) {
                    throw new IllegalArgumentException("Fila " + rowNum + ": LegajoEvaluado es obligatorio");
                }
                int legajoEvaluado = Integer.parseInt(legajoEvaluadoStr);


                String legajoEvaluadorStr = formatter.formatCellValue(row.getCell(1));
                if (legajoEvaluadorStr == null || legajoEvaluadorStr.isBlank()) {
                    throw new IllegalArgumentException("Fila " + rowNum + ": LegajoEvaluador es obligatorio");
                }
                int legajoEvaluador = Integer.parseInt(legajoEvaluadorStr);


                String legajoValidadorStr = formatter.formatCellValue(row.getCell(2));
                if (legajoValidadorStr == null || legajoValidadorStr.isBlank()) {
                    throw new IllegalArgumentException("Fila " + rowNum + ": LegajoValidador es obligatorio");
                }
                int legajoValidador = Integer.parseInt(legajoValidadorStr);

                String nombre = formatter.formatCellValue(row.getCell(3));


                LocalDate fecha = null;
                Cell fechaCell = row.getCell(4);
                if (fechaCell != null) {
                    if (fechaCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(fechaCell)) {
                        fecha = fechaCell.getDateCellValue()
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
                    } else {
                        String fechaStr = formatter.formatCellValue(fechaCell);
                        if (!fechaStr.isBlank()) {
                            fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern(JdbcDateJavaType.DATE_FORMAT));
                        }
                    }
                }
                if (fecha == null) {
                    throw new IllegalArgumentException("Fila " + rowNum + ": Fecha es obligatoria");
                }

                String puesto = formatter.formatCellValue(row.getCell(5));
                String celula = formatter.formatCellValue(row.getCell(6));
                String disponibilidad = formatter.formatCellValue(row.getCell(7));

                evaluaciones.add(new NominaEvaluacionDto(
                        legajoEvaluado, legajoEvaluador, legajoValidador,
                        nombre, fecha, puesto, celula, disponibilidad
                ));
            }
        }

        return evaluaciones;
    }


    @Transactional
    public ResultadoImportacionEvaluacionDto importarEvaluaciones(List<NominaEvaluacionDto> nominaEvaluaciones) {
        int total = nominaEvaluaciones.size();
        int creados = 0;
        int errores = 0;
        List<String> mensajesError = new ArrayList<>();
        List<Evaluacion> evaluacionesCargadas = new ArrayList<>();

        for (NominaEvaluacionDto dto : nominaEvaluaciones) {
            try {
                // Buscar usuarios por legajo
                Usuario evaluado = userRepository.findByLegajo(dto.legajoEvaluado())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "No se encontro evaluado con legajo: " + dto.legajoEvaluado()));

                Usuario evaluador = userRepository.findByLegajo(dto.legajoEvaluador())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "No se encontro evaluador con legajo: " + dto.legajoEvaluador()));

                Usuario validador = userRepository.findByLegajo(dto.legajoValidador())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "No se encontro validador con legajo: " + dto.legajoValidador()));

                Evaluacion evaluacion = Evaluacion.builder()
                        .nombre(dto.nombre() != null && !dto.nombre().isBlank()
                                ? dto.nombre()
                                : "Evaluacion " + evaluado.getNombre() + " " + evaluado.getApellido())
                        .fecha(dto.fecha())
                        .estado(EstadoEvaluacion.PENDIENTE)
                        .evaluado(evaluado)
                        .evaluador(evaluador)
                        .validador(validador)
                        .puesto(dto.puesto())
                        .celula(dto.celula())
                        .disponibilidad(dto.disponibilidad())
                        .resultadoCalculado(0.0)
                        .creado(new Date())
                        .ultimaModificacion(new Date())
                        .build();

                Evaluacion saved = repository.save(evaluacion);
                evaluacionesCargadas.add(saved);
                creados++;

            } catch (Exception e) {
                errores++;
                mensajesError.add("Fila con evaluado legajo " + dto.legajoEvaluado() + ": " + e.getMessage());
            }
        }

        return new ResultadoImportacionEvaluacionDto(
                total, creados, errores, mensajesError, evaluacionesCargadas
        );
    }

    @Transactional
    public ResultadoImportacionEvaluacionDto importarEvaluacionesDesdeExcel(MultipartFile file) throws IOException {
        List<NominaEvaluacionDto> nominaEvaluaciones = leerEvaluacionesExcel(file);
        return importarEvaluaciones(nominaEvaluaciones);
    }
}


