package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.AuthDTO;
import com.ecommerce.ecommerce.dto.LoginResponseDTO;
import com.ecommerce.ecommerce.exception.*;
import com.ecommerce.ecommerce.infra.security.TokenService;
import com.ecommerce.ecommerce.model.Produto;
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

        if(usuarioRepository.findById(usuario.getId()).isPresent()){
            throw new ProdutoExistsException(usuario.getId());
        }

        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
        usuario.setSenha(null);
        return usuario;
    }

    @Override
    public ResponseEntity logarUsuario(AuthDTO usuario){
        var usuarioSenha = new UsernamePasswordAuthenticationToken(usuario.getLogin(),usuario.getSenha());
        var auth = authenticationManager.authenticate(usuarioSenha);

        var token = tokenService.generatedToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Override
    public List<Usuario> visualizarUsuarios(){ return usuarioRepository.findAll(); }

    @Override
    public Usuario visualizarUsuario(Integer id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));

        usuario.setSenha(null);
        return usuario;
    }

    @Override
    public void deletarUsuario(Integer id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));

        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario atualizarUsuario(Integer id, Usuario usuarioAtualizado){

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));

        if(!usuario.getId().equals(usuarioAtualizado.getId())) {
            throw new UsuarioUpdateIdException();
        }

        if(!usuario.getRole().equals(usuarioAtualizado.getRole())) {
            throw new UsuarioUpdateRoleException();
        }

        if(usuarioAtualizado.getSenha() == null) {
            throw new UsuarioUpdateSenhaException();
        }

        deletarUsuario(usuario.getId());
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        usuarioRepository.save(usuarioAtualizado);
        usuarioAtualizado.setSenha(null);

        return usuarioAtualizado;
    }
}