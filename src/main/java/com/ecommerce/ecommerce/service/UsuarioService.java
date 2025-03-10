package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.AuthDTO;
import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.repository.interfaces.IUsuarioRepository;
import com.ecommerce.ecommerce.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService{

    private final IUsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository,
                          AuthenticationManager authenticationManager){
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
    }

    public Usuario cadastrarUsuario(Usuario usuario){
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }
    public ResponseEntity logarUsuario(AuthDTO usuario){
        var usuarioSenha = new UsernamePasswordAuthenticationToken(usuario.getLogin(),usuario.getSenha());
        var auth = authenticationManager.authenticate(usuarioSenha);

        return ResponseEntity.ok().build();
    }
}