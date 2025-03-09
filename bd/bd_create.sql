CREATE TABLE produto (
    id INT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    preco DECIMAL(10) NOT NULL,
    quantidade_em_estoque INT NOT NULL
);