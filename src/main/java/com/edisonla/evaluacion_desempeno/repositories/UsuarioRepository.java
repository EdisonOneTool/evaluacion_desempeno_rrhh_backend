package com.edisonla.evaluacion_desempeno.repositories;

import com.edisonla.evaluacion_desempeno.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<UserInfo,Long> {

    Optional<UserInfo> findByEmail(String email);
}
