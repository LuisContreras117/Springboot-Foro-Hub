package com.cursoalura.forohub.dto.respuesta;

import com.cursoalura.forohub.dto.topico.DetalleTopicoDTO;
import com.cursoalura.forohub.dto.usuario.DetalleUsuarioDTO;
import com.cursoalura.forohub.entity.RespuestaEntity;

public record DetalleRespuestaDTO(
        Long id,
        String mensaje,
        DetalleUsuarioDTO autor,
        DetalleTopicoDTO topico,
        String fechaCreacion,
        Boolean solucion
) {
    public DetalleRespuestaDTO(RespuestaEntity respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                new DetalleUsuarioDTO(respuesta.getAutor()),
                new DetalleTopicoDTO(respuesta.getTopico()),
                respuesta.getFechaCreacion().toString(),
                respuesta.getSolucion()
        );
    }
}
