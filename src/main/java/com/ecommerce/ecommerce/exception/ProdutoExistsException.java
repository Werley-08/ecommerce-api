package com.ecommerce.ecommerce.exception;

public class ProdutoExistsException extends RuntimeException {

    public ProdutoExistsException(Integer id) {
        super("Já existe um produto cadastrado com o id: " + id);
    }
}