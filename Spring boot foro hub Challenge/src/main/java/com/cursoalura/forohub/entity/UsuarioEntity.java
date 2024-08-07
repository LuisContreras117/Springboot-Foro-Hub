package com.cursoalura.forohub.entity;

import com.cursoalura.forohub.dto.usuario.ActualizarUsuarioDTO;
import com.cursoalura.forohub.dto.usuario.RegistroUsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String contrasena;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "perfiles")
    private PerfilEntity perfil;

    public UsuarioEntity(RegistroUsuarioDTO datos, String contrasena, PerfilEntity perfil) {
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.contrasena = contrasena;
        this.perfil = perfil;
    }

    public void actualizar(ActualizarUsuarioDTO datos) {
        if (datos.nombre() != null)
            this.nombre = datos.nombre();
        if (datos.email() != null)
            this.email = datos.email();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(perfil.getNombre()));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
