package com.cursoalura.forohub.dto.respuesta;

import jakarta.validation.constraints.NotNull;

public record ActualizarRespuestaDTO(
        @NotNull
        Long id,
        String mensaje,
        Long topico,
        Long autor,
        Boolean solucion
) {
}
