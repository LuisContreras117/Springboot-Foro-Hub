package com.cursoalura.forohub.controller;

import com.cursoalura.forohub.dto.perfil.DetallePerfilDTO;
import com.cursoalura.forohub.service.PerfilService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfiles")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {
    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public ResponseEntity<List<DetallePerfilDTO>> listarPerfiles() {
        return ResponseEntity.ok(perfilService.listar());
    }
}
