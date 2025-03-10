package com.ecommerce.ecommerce.repository.implement;

import com.ecommerce.ecommerce.repository.UsuarioRepository;
import com.ecommerce.ecommerce.repository.interfaces.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryImplement implements IUsuarioRepository {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioRepositoryImplement(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails findByLogin(String login){
        return usuarioRepository.findByLogin(login);
    }
}