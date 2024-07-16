package com.cursoalura.forohub.service;

import com.cursoalura.forohub.dto.usuario.ActualizarUsuarioDTO;
import com.cursoalura.forohub.dto.usuario.DetalleUsuarioDTO;
import com.cursoalura.forohub.dto.usuario.RegistroUsuarioDTO;
import com.cursoalura.forohub.dto.usuario.UsuarioListaDTO;
import com.cursoalura.forohub.entity.PerfilEntity;
import com.cursoalura.forohub.entity.UsuarioEntity;
import com.cursoalura.forohub.repository.PerfilRepository;
import com.cursoalura.forohub.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public DetalleUsuarioDTO registrar(RegistroUsuarioDTO datos) {
        PerfilEntity perfil = perfilRepository.findById(datos.perfil())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Perfil no valido"));
        var contrasenaEncriptada = passwordEncoder.encode(datos.contrasena());
        var usuario = new UsuarioEntity(datos, contrasenaEncriptada, perfil);
        return new DetalleUsuarioDTO(usuarioRepository.save(usuario));
    }

    public Page<UsuarioListaDTO> listar(Pageable paginacion) {
        return usuarioRepository.findAll(paginacion).map(UsuarioListaDTO::new);
    }

    @Transactional
    public DetalleUsuarioDTO actualizar(ActualizarUsuarioDTO datos) {
        var usuario = usuarioRepository.getReferenceById(datos.id());
        usuario.actualizar(datos);
        return new DetalleUsuarioDTO(usuario);
    }

    @Transactional
    public void eliminar(Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
    }

    public DetalleUsuarioDTO obtener(Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        return new DetalleUsuarioDTO(usuario);
    }
}
