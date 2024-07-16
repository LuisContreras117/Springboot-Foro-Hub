package com.cursoalura.forohub.service;

import com.cursoalura.forohub.dto.topico.ActualizarTopicoDTO;
import com.cursoalura.forohub.dto.topico.DetalleTopicoDTO;
import com.cursoalura.forohub.dto.topico.RegistroTopicoDTO;
import com.cursoalura.forohub.dto.topico.TopicoListaDTO;
import com.cursoalura.forohub.entity.TopicoEntity;
import com.cursoalura.forohub.repository.CursoRepository;
import com.cursoalura.forohub.repository.TopicoRepository;
import com.cursoalura.forohub.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public DetalleTopicoDTO registrar(RegistroTopicoDTO datos) {
        topicoRepository.findByTituloAndMensaje(datos.titulo(), datos.mensaje())
                .ifPresent(topico -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Topico duplicado");
                });
        var autor = usuarioRepository.getReferenceById(datos.autor());
        var curso = cursoRepository.getReferenceById(datos.curso());
        var topico = new TopicoEntity(datos, autor, curso);
        return new DetalleTopicoDTO(topicoRepository.save(topico));
    }

    public Page<TopicoListaDTO> listar(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(TopicoListaDTO::new);
    }

    @Transactional
    public DetalleTopicoDTO actualizar(ActualizarTopicoDTO datos) {
        var topico = topicoRepository.getReferenceById(datos.id());
        var autor = usuarioRepository.getReferenceById(datos.autor());
        var curso = cursoRepository.getReferenceById(datos.curso());
        topico.actualizar(datos, autor, curso);
        return new DetalleTopicoDTO(topico);
    }

    @Transactional
    public void eliminar(Long id) {
        var topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
    }

    public DetalleTopicoDTO obtener(Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return new DetalleTopicoDTO(topico);
    }
}
