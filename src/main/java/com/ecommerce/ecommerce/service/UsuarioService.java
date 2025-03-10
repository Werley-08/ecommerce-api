package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.repository.interfaces.IUsuarioRepository;
import com.ecommerce.ecommerce.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService{

    private final IUsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrarUsuario(Usuario usuario){
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }
}