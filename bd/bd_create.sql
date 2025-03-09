CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    preco DECIMAL(10, 2) NOT NULL,
    quantidade_em_estoque INT NOT NULL
);