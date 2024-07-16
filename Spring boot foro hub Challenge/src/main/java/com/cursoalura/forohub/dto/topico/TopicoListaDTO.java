package com.cursoalura.forohub.dto.topico;

import com.cursoalura.forohub.entity.TopicoEntity;

public record TopicoListaDTO(
        Long id,
        String titulo,
        String mensaje,
        String fechaCreacion,
        String estado,
        String autor,
        String curso
) {
    public TopicoListaDTO(TopicoEntity topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion().toString(),
                topico.getStatus().toString(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
