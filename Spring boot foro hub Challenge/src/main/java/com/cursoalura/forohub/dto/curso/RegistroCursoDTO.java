package com.cursoalura.forohub.dto.curso;

import jakarta.validation.constraints.NotBlank;

public record RegistroCursoDTO(
        @NotBlank
        String nombre,
        @NotBlank
        String categoria
) {
}
