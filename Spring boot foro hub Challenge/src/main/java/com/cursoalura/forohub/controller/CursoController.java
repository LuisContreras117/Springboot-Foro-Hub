package com.cursoalura.forohub.controller;

import com.cursoalura.forohub.dto.curso.ActualizarCursoDTO;
import com.cursoalura.forohub.dto.curso.DetalleCursoDTO;
import com.cursoalura.forohub.dto.curso.RegistroCursoDTO;
import com.cursoalura.forohub.service.CursoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<DetalleCursoDTO> registrarCurso(@RequestBody @Valid RegistroCursoDTO datos,
                                                          UriComponentsBuilder uriComponentsBuilder) {
        var curso = cursoService.registrar(datos);
        URI url = uriComponentsBuilder.path("/cursos/{id}")
                .buildAndExpand(curso.id())
                .toUri();
        return ResponseEntity.created(url).body(curso);
    }

    @GetMapping
    public ResponseEntity<Page<DetalleCursoDTO>> listarCursos(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(cursoService.listar(paginacion));
    }

    @PutMapping
    public ResponseEntity<DetalleCursoDTO> actualizarCurso(@RequestBody @Valid ActualizarCursoDTO datos) {
        return ResponseEntity.ok(cursoService.actualizar(datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarCurso(@PathVariable Long id) {
        cursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleCursoDTO> obtenerCurso(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.obtener(id));
    }
}
