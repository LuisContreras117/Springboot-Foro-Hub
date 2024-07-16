package com.cursoalura.forohub.entity;

import com.cursoalura.forohub.dto.respuesta.ActualizarRespuestaDTO;
import com.cursoalura.forohub.dto.respuesta.RegistroRespuestaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuesta")
@Entity(name = "Respuesta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RespuestaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne
    @JoinColumn(name = "topico")
    private TopicoEntity topico;
    private LocalDateTime fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "autor")
    private UsuarioEntity autor;
    private Boolean solucion;

    public RespuestaEntity(RegistroRespuestaDTO datos, UsuarioEntity autor, TopicoEntity topico) {
        this.mensaje = datos.mensaje();
        this.topico = topico;
        this.autor = autor;
    }

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.solucion = false;
    }

    public void actualizar(ActualizarRespuestaDTO datos, UsuarioEntity autor, TopicoEntity topico) {
        if (datos.mensaje() != null)
            this.mensaje = datos.mensaje();
        if (datos.solucion() != null)
            this.solucion = datos.solucion();
        if (autor != null)
            this.autor = autor;
        if (topico != null)
            this.topico = topico;
    }
}
