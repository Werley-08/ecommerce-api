package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.AuthDTO;
import com.ecommerce.ecommerce.dto.LoginResponseDTO;
import com.ecommerce.ecommerce.infra.security.TokenService;
import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.repository.interfaces.IUsuarioRepository;
import com.ecommerce.ecommerce.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService{

    private final IUsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository,
                          AuthenticationManager authenticationManager,
                          TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public Usuario cadastrarUsuario(Usuario usuario){
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public ResponseEntity logarUsuario(AuthDTO usuario){
        var usuarioSenha = new UsernamePasswordAuthenticationToken(usuario.getLogin(),usuario.getSenha());
        var auth = authenticationManager.authenticate(usuarioSenha);

        var token = tokenService.generatedToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Override
    public List<Usuario> visualizarUsuarios(){
        return usuarioRepository.findAll();
    }
}