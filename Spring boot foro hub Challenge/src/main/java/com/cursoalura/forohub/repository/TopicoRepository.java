package com.cursoalura.forohub.repository;

import com.cursoalura.forohub.entity.TopicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<TopicoEntity, Long> {
    Optional<TopicoEntity> findByTituloAndMensaje(String titulo, String mensaje);
}
