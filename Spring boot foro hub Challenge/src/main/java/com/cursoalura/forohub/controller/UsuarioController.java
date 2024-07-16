package com.cursoalura.forohub.controller;

import com.cursoalura.forohub.dto.usuario.ActualizarUsuarioDTO;
import com.cursoalura.forohub.dto.usuario.DetalleUsuarioDTO;
import com.cursoalura.forohub.dto.usuario.UsuarioListaDTO;
import com.cursoalura.forohub.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioListaDTO>> listarUsuarios(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(usuarioService.listar(paginacion));
    }

    @PutMapping
    public ResponseEntity<DetalleUsuarioDTO> actualizarUsuario(@RequestBody @Valid ActualizarUsuarioDTO datos) {
        return ResponseEntity.ok(usuarioService.actualizar(datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleUsuarioDTO> obtenerUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtener(id));
    }
}
