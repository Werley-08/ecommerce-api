package com.ecommerce.ecommerce.repository.implement;

import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.repository.UsuarioRepository;
import com.ecommerce.ecommerce.repository.interfaces.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> findAll(){ return usuarioRepository.findAll(); }

    @Override
    public Optional<Usuario> findById(Integer id){ return usuarioRepository.findById(id); }
}