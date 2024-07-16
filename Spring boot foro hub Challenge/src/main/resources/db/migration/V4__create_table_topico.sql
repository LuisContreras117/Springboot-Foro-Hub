CREATE TABLE topico (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    mensaje VARCHAR(500) NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    autor BIGINT NOT NULL,
    curso BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (autor) REFERENCES usuario(id),
    FOREIGN KEY (curso) REFERENCES curso(id),
    CONSTRAINT unique_titulo_mensaje UNIQUE (titulo, mensaje)
);
