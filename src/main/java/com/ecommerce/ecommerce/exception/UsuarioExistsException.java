package com.ecommerce.ecommerce.exception;

public class UsuarioExistsException extends RuntimeException{

    UsuarioExistsException(Integer id){ super("Já existe um usuario cadastrado com o id: " + id); }
}
