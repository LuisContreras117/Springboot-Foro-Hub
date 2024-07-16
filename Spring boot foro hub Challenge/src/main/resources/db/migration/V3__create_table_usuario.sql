CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(300) NOT NULL,
    perfiles BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (perfiles) REFERENCES perfil(id)
);
