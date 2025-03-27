package com.ecommerce.ecommerce.service.interfaces;

import com.ecommerce.ecommerce.dto.AuthDTO;
import com.ecommerce.ecommerce.model.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUsuarioService{
    Usuario cadastrarUsuario(Usuario usuario);
    ResponseEntity logarUsuario(AuthDTO usuario);
    List<Usuario> visualizarUsuarios();
}