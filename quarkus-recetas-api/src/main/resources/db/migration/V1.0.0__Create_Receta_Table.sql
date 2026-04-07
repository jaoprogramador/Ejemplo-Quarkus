CREATE TABLE Receta (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    ingredientes VARCHAR(1000),
    tiempoPreparacion INTEGER,
    dificultad VARCHAR(50),
    fechaPublicacion DATE,
    fechaCreacion TIMESTAMP,
    fechaActuali TIMESTAMP
);