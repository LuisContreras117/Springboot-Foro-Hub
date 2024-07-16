package com.cursoalura.forohub.dto.respuesta;

import com.cursoalura.forohub.entity.RespuestaEntity;
import com.cursoalura.forohub.entity.TopicoEntity;

public record RespuestaListaDTO(
        Long id,
        String mensaje,
        String autor,
        String topico,
        String fechaCreacion,
        Boolean solucion
) {
    public RespuestaListaDTO(RespuestaEntity respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getAutor().getNombre(),
                respuesta.getTopico().getTitulo(),
                respuesta.getFechaCreacion().toString(),
                respuesta.getSolucion()
        );
    }
}
