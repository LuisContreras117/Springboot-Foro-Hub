package com.cursoalura.forohub.dto.topico;

import jakarta.validation.constraints.NotNull;

public record ActualizarTopicoDTO(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        Long autor,
        Long curso
) {
}
