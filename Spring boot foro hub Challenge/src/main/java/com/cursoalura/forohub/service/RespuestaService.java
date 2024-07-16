package com.cursoalura.forohub.service;

import com.cursoalura.forohub.dto.respuesta.ActualizarRespuestaDTO;
import com.cursoalura.forohub.dto.respuesta.DetalleRespuestaDTO;
import com.cursoalura.forohub.dto.respuesta.RegistroRespuestaDTO;
import com.cursoalura.forohub.dto.respuesta.RespuestaListaDTO;
import com.cursoalura.forohub.entity.RespuestaEntity;
import com.cursoalura.forohub.repository.RespuestaRepository;
import com.cursoalura.forohub.repository.TopicoRepository;
import com.cursoalura.forohub.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {
    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public DetalleRespuestaDTO registrar(RegistroRespuestaDTO datos) {
        var autor = usuarioRepository.getReferenceById(datos.autor());
        var topico = topicoRepository.getReferenceById(datos.topico());
        var respuesta = new RespuestaEntity(datos, autor, topico);
        return new DetalleRespuestaDTO(respuestaRepository.save(respuesta));
    }

    public Page<RespuestaListaDTO> listar(Pageable paginacion) {
        return respuestaRepository.findAll(paginacion).map(RespuestaListaDTO::new);
    }

    @Transactional
    public DetalleRespuestaDTO actualizar(ActualizarRespuestaDTO datos) {
        var respuesta = respuestaRepository.getReferenceById(datos.id());
        var autor = usuarioRepository.getReferenceById(datos.autor());
        var topico = topicoRepository.getReferenceById(datos.topico());
        respuesta.actualizar(datos, autor, topico);
        return new DetalleRespuestaDTO(respuesta);
    }

    @Transactional
    public void eliminar(Long id) {
        var respuesta = respuestaRepository.getReferenceById(id);
        respuestaRepository.delete(respuesta);
    }

    public DetalleRespuestaDTO obtener(Long id) {
        var respuesta = respuestaRepository.getReferenceById(id);
        return new DetalleRespuestaDTO(respuesta);
    }
}
