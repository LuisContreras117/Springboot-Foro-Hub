package com.cursoalura.forohub.dto.curso;

import com.cursoalura.forohub.entity.CursoEntity;

public record DetalleCursoDTO(
        Long id,
        String nombre,
        String categoria
) {
    public DetalleCursoDTO(CursoEntity curso) {
        this(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
    }
}
