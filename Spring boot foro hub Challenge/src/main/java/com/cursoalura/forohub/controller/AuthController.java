package com.cursoalura.forohub.controller;

import com.cursoalura.forohub.dto.security.JWTTokenDTO;
import com.cursoalura.forohub.dto.usuario.AutenticacionUsuarioDTO;
import com.cursoalura.forohub.dto.usuario.DetalleUsuarioDTO;
import com.cursoalura.forohub.dto.usuario.RegistroUsuarioDTO;
import com.cursoalura.forohub.entity.UsuarioEntity;
import com.cursoalura.forohub.service.TokenService;
import com.cursoalura.forohub.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<JWTTokenDTO> autenticarUsuario(@RequestBody @Valid AutenticacionUsuarioDTO usuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(usuario.email(),
                usuario.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWToken = tokenService.generarToken((UsuarioEntity) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(JWToken));
    }

    @PostMapping("/register")
    public ResponseEntity<DetalleUsuarioDTO> registrarUsuario(@RequestBody @Valid RegistroUsuarioDTO datos,
                                                              UriComponentsBuilder uriComponentsBuilder) {
        var usuario = usuarioService.registrar(datos);
        URI url = uriComponentsBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuario.id())
                .toUri();
        return ResponseEntity.created(url).body(usuario);
    }
}
