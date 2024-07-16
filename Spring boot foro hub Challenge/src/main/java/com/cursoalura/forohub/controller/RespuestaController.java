package com.cursoalura.forohub.controller;

import com.cursoalura.forohub.dto.respuesta.ActualizarRespuestaDTO;
import com.cursoalura.forohub.dto.respuesta.DetalleRespuestaDTO;
import com.cursoalura.forohub.dto.respuesta.RegistroRespuestaDTO;
import com.cursoalura.forohub.dto.respuesta.RespuestaListaDTO;
import com.cursoalura.forohub.service.RespuestaService;
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
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {
    @Autowired
    private RespuestaService respuestaService;

    @PostMapping
    public ResponseEntity<DetalleRespuestaDTO> registrarRespuesta(@RequestBody @Valid RegistroRespuestaDTO datos,
                                                               UriComponentsBuilder uriComponentsBuilder) {
        var respuesta = respuestaService.registrar(datos);
        URI url = uriComponentsBuilder.path("/respúestas/{id}")
                .buildAndExpand(respuesta.id())
                .toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<RespuestaListaDTO>> listarRespuestas(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(respuestaService.listar(paginacion));
    }

    @PutMapping
    public ResponseEntity<DetalleRespuestaDTO> actualizarRespuesta(@RequestBody @Valid ActualizarRespuestaDTO datos) {
        return ResponseEntity.ok(respuestaService.actualizar(datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarRespuesta(@PathVariable Long id) {
        respuestaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleRespuestaDTO> obtenerRespuesta(@PathVariable Long id) {
        return ResponseEntity.ok(respuestaService.obtener(id));
    }
}
