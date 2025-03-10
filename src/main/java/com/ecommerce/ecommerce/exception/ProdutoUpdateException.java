package com.ecommerce.ecommerce.exception;

public class ProdutoUpdateException extends RuntimeException {

    public ProdutoUpdateException(){
        super("O id do produto não pode ser atualizado");
    }
}