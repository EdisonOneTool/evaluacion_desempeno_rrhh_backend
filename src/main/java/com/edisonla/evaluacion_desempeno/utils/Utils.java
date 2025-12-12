package com.edisonla.evaluacion_desempeno.utils;

import com.edisonla.evaluacion_desempeno.entities.Usuario;
import com.edisonla.evaluacion_desempeno.enums.EstadoEvaluacion;
import com.edisonla.evaluacion_desempeno.enums.Roles;
import com.edisonla.evaluacion_desempeno.repositories.UsuarioRepository;
import com.edisonla.evaluacion_desempeno.services.JwtService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class Utils {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Transactional(readOnly = true)
    public boolean checkIsAdmin(String token) {
        Usuario me = usuarioRepository.findByEmail(jwtService.extractEmail(token))
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el elemento con token: " + token));
        return (me.getRoles() != null && me.getRoles().toLowerCase().contains(Roles.ADMIN.toString().toLowerCase()));
    }

    public static Roles rolesFromString(String givenName) {
        for (Roles rol : Roles.values()) {
            if (rol.name().equalsIgnoreCase(givenName)) { // Case-insensitive check
                return rol;
            }
        }
        // Handle no match, e.g., return null or throw a custom exception
        return null;
    }

    public static EstadoEvaluacion estadoFromString(String givenName) {
        for (EstadoEvaluacion estadoEvaluacion : EstadoEvaluacion.values()) {
            if (estadoEvaluacion.name().equalsIgnoreCase(givenName)) { // Case-insensitive check
                return estadoEvaluacion;
            }
        }
        // Handle no match, e.g., return null or throw a custom exception
        return null;
    }
}
