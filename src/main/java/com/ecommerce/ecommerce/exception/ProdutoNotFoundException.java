package com.ecommerce.ecommerce.exception;

public class ProdutoNotFoundException extends RuntimeException {

    public ProdutoNotFoundException(Integer id) {
        super("Produto não encontrado com o id: " + id);
    }
}