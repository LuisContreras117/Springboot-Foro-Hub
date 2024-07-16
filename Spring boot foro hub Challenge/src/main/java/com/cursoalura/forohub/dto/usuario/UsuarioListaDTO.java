package com.cursoalura.forohub.dto.usuario;

import com.cursoalura.forohub.dto.perfil.DetallePerfilDTO;
import com.cursoalura.forohub.entity.UsuarioEntity;

public record UsuarioListaDTO(
        Long id,
        String nombre,
        String email,
        DetallePerfilDTO perfil
) {
    public UsuarioListaDTO(UsuarioEntity usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                new DetallePerfilDTO(usuario.getPerfil())
        );
    }
}
