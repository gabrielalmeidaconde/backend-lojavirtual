-- Tabelas de relacionamento Many-to-Many com PRIMARY KEY composta
-- Serão criadas apenas se não existirem

CREATE TABLE IF NOT EXISTS jogo_genero (
    jogo_id BIGINT NOT NULL,
    genero_id BIGINT NOT NULL,
    PRIMARY KEY (jogo_id, genero_id),
    CONSTRAINT fk_jogo_genero_jogo FOREIGN KEY (jogo_id) REFERENCES jogo(id) ON DELETE CASCADE,
    CONSTRAINT fk_jogo_genero_genero FOREIGN KEY (genero_id) REFERENCES genero(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS jogo_usuario (
    jogo_id BIGINT NOT NULL,
    usuario_email VARCHAR(255) NOT NULL,
    PRIMARY KEY (jogo_id, usuario_email),
    CONSTRAINT fk_jogo_usuario_jogo FOREIGN KEY (jogo_id) REFERENCES jogo(id) ON DELETE CASCADE,
    CONSTRAINT fk_jogo_usuario_usuario FOREIGN KEY (usuario_email) REFERENCES usuario(email) ON DELETE CASCADE
) ENGINE=InnoDB;
