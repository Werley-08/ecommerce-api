package com.ecommerce.ecommerce.exception;

public class UsuarioUpdateIdException extends RuntimeException {

    public UsuarioUpdateIdException() { super("O id do usuario não pode ser atualizado"); }
}