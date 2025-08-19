CREATE TABLE topics (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) DEFAULT 'ABIERTO',
    autor VARCHAR(100) NOT NULL,
    curso VARCHAR(100) NOT NULL,
    UNIQUE KEY unique_topic(titulo, mensaje)
);
