CREATE TABLE respuesta (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje VARCHAR(1000) NOT NULL,
    topico BIGINT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    autor BIGINT NOT NULL,
    solucion BOOLEAN NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (topico) REFERENCES topico(id),
    FOREIGN KEY (autor) REFERENCES usuario(id)
);
