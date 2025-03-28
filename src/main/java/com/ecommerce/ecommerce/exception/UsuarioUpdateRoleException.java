package com.ecommerce.ecommerce.exception;

public class UsuarioUpdateRoleException extends RuntimeException {

    public UsuarioUpdateRoleException() { super("O acesso do usuario não pode ser atualizado"); }
}