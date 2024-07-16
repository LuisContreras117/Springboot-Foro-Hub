package com.cursoalura.forohub.dto.perfil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistroPerfilDTO(
        @NotBlank
        @Size(max = 25)
        String nombre
) {
}
