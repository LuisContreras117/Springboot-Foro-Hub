package com.cursoalura.forohub.dto.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroRespuestaDTO(
        @NotBlank
        String mensaje,
        @NotNull
        Long topico,
        @NotNull
        Long autor
) {
}
