package com.cursoalura.forohub.entity;

import com.cursoalura.forohub.dto.topico.ActualizarTopicoDTO;
import com.cursoalura.forohub.dto.topico.RegistroTopicoDTO;
import com.cursoalura.forohub.dto.topico.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topico")
@Entity(name = "Topico")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TopicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor")
    private UsuarioEntity autor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso")
    private CursoEntity curso;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RespuestaEntity> respuestas;

    public TopicoEntity(RegistroTopicoDTO datos, UsuarioEntity autor, CursoEntity curso) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = autor;
        this.curso = curso;
    }

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.status = Status.NO_RESPONDIDO;
    }

    public void actualizar(ActualizarTopicoDTO datos, UsuarioEntity autor, CursoEntity curso) {
        if (datos.titulo() != null)
            this.titulo = datos.titulo();
        if (datos.mensaje() != null)
            this.mensaje = datos.mensaje();
        if (autor != null)
            this.autor = autor;
        if (curso != null)
            this.curso = curso;
    }
}
