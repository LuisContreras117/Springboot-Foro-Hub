package com.cursoalura.forohub.entity;

import com.cursoalura.forohub.dto.curso.ActualizarCursoDTO;
import com.cursoalura.forohub.dto.curso.RegistroCursoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "curso")
@Entity(name = "Curso")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;

    public CursoEntity(RegistroCursoDTO datos) {
        this.nombre = datos.nombre();
        this.categoria = datos.categoria();
    }

    public void actualizar(ActualizarCursoDTO datos) {
        if (datos.nombre() != null)
            this.nombre = datos.nombre();
        if (datos.categoria() != null)
            this.categoria = datos.categoria();
    }
}
