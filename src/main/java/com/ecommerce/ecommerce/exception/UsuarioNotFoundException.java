package com.ecommerce.ecommerce.exception;

public class UsuarioNotFoundException extends RuntimeException {

    public UsuarioNotFoundException(Integer id) {
        super("Usuario não encontrado com o id: " + id);
    }
}
