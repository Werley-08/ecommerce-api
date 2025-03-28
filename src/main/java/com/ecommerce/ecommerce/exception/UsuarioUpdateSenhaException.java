package com.ecommerce.ecommerce.exception;

public class UsuarioUpdateSenhaException extends RuntimeException {

    public UsuarioUpdateSenhaException() { super("A senha não pode ser nula");}
}
