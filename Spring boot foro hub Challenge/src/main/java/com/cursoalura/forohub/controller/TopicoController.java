package com.cursoalura.forohub.controller;

import com.cursoalura.forohub.dto.topico.ActualizarTopicoDTO;
import com.cursoalura.forohub.dto.topico.DetalleTopicoDTO;
import com.cursoalura.forohub.dto.topico.RegistroTopicoDTO;
import com.cursoalura.forohub.dto.topico.TopicoListaDTO;
import com.cursoalura.forohub.service.TopicoService;
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
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DetalleTopicoDTO> registrarTopico(@RequestBody @Valid RegistroTopicoDTO datos,
                                                           UriComponentsBuilder uriComponentsBuilder) {
        var topico = topicoService.registrar(datos);
        URI url = uriComponentsBuilder.path("/topicos/{id}")
                .buildAndExpand(topico.id())
                .toUri();
        return ResponseEntity.created(url).body(topico);
    }

    @GetMapping
    public ResponseEntity<Page<TopicoListaDTO>> listarTopicos(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(topicoService.listar(paginacion));
    }

    @PutMapping
    public ResponseEntity<DetalleTopicoDTO> actualizarCurso(@RequestBody @Valid ActualizarTopicoDTO datos) {
        return ResponseEntity.ok(topicoService.actualizar(datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarCurso(@PathVariable Long id) {
        topicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleTopicoDTO> obtenerCurso(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.obtener(id));
    }
}
