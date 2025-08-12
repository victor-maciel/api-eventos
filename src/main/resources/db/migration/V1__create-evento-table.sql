CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE evento (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao VARCHAR(250) NOT NULL,
    img_url VARCHAR(250) NOT NULL,
    evento_url VARCHAR(250) NOT NULL,
    date TIMESTAMP NOT NULL,
    presencial BOOLEAN NOT NULL
);
