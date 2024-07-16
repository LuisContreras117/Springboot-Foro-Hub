package com.cursoalura.forohub.dto.usuario;

import com.cursoalura.forohub.dto.perfil.DetallePerfilDTO;
import com.cursoalura.forohub.entity.UsuarioEntity;

public record DetalleUsuarioDTO(
        Long id,
        String nombre,
        String email,
        String contrasena,
        DetallePerfilDTO perfil
) {
    public DetalleUsuarioDTO(UsuarioEntity usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getContrasena(),
                new DetallePerfilDTO(usuario.getPerfil())
        );
    }
}
