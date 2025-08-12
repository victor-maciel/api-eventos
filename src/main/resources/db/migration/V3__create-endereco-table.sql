CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE endereco (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    cidade VARCHAR(100) NOT NULL,
    uf VARCHAR(100) NOT NULL,
    rua VARCHAR(100) NOT NULL,
    numero VARCHAR(100) NOT NULL,
    cep VARCHAR(100) NOT NULL,
    evento_id UUID,
    FOREIGN KEY (evento_id) REFERENCES evento(id) ON DELETE CASCADE
);
