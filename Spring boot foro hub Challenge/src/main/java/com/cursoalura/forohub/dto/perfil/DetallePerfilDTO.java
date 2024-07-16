package com.cursoalura.forohub.dto.perfil;

import com.cursoalura.forohub.entity.PerfilEntity;

public record DetallePerfilDTO(
        Long id,
        String nombre
) {
    public DetallePerfilDTO(PerfilEntity perfil) {
        this(
                perfil.getId(),
                perfil.getNombre()
        );
    }
}
