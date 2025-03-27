package com.ecommerce.ecommerce.repository.interfaces;

import com.ecommerce.ecommerce.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUsuarioRepository{
    UserDetails findByLogin(String login);
    Usuario save(Usuario usuario);
    List<Usuario> findAll();
}