package com.ecommerce.ecommerce.repository.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUsuarioRepository{
    UserDetails findByLogin(String login);
}