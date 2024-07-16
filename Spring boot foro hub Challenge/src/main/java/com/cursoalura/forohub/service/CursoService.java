package com.cursoalura.forohub.service;

import com.cursoalura.forohub.dto.curso.ActualizarCursoDTO;
import com.cursoalura.forohub.dto.curso.DetalleCursoDTO;
import com.cursoalura.forohub.dto.curso.RegistroCursoDTO;
import com.cursoalura.forohub.entity.CursoEntity;
import com.cursoalura.forohub.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public DetalleCursoDTO registrar(RegistroCursoDTO datos) {
        var curso = new CursoEntity(datos);
        return new DetalleCursoDTO(cursoRepository.save(curso));
    }

    public Page<DetalleCursoDTO> listar(Pageable paginacion) {
        return cursoRepository.findAll(paginacion).map(DetalleCursoDTO::new);
    }

    @Transactional
    public DetalleCursoDTO actualizar(ActualizarCursoDTO datos) {
        var curso = cursoRepository.getReferenceById(datos.id());
        curso.actualizar(datos);
        return new DetalleCursoDTO(curso);
    }

    @Transactional
    public void eliminar(Long id) {
        var curso = cursoRepository.getReferenceById(id);
        cursoRepository.delete(curso);
    }

    public DetalleCursoDTO obtener(Long id) {
        var curso = cursoRepository.getReferenceById(id);
        return new DetalleCursoDTO(curso);
    }
}
