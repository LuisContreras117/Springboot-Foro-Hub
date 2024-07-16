package com.cursoalura.forohub.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActualizarUsuarioDTO(
        @NotNull
        Long id,
        String nombre,
        String email
) {
}
