package com.cursoalura.forohub.dto.topico;

import com.cursoalura.forohub.dto.curso.DetalleCursoDTO;
import com.cursoalura.forohub.dto.usuario.DetalleUsuarioDTO;
import com.cursoalura.forohub.entity.TopicoEntity;

public record DetalleTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        String fechaCreacion,
        String estado,
        DetalleUsuarioDTO autor,
        DetalleCursoDTO curso
) {
    public DetalleTopicoDTO(TopicoEntity topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion().toString(),
                topico.getStatus().toString(),
                new DetalleUsuarioDTO(topico.getAutor()),
                new DetalleCursoDTO(topico.getCurso())
        );
    }
}
