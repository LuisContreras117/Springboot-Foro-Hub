package com.cursoalura.forohub.dto.curso;

import jakarta.validation.constraints.NotNull;

public record ActualizarCursoDTO(
        @NotNull
        Long id,
        String nombre,
        String categoria
) {
}
