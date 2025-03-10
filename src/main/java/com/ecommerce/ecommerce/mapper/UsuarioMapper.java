package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.UsuarioDTO;
import com.ecommerce.ecommerce.model.Usuario;

public interface UsuarioMapper{

    static Usuario toModel(UsuarioDTO usuarioDTO){
        return new Usuario(
                usuarioDTO.getId(),
                usuarioDTO.getLogin(),
                usuarioDTO.getSenha(),
                usuarioDTO.getRole()
        );
    }

    static UsuarioDTO toDTO(Usuario usuario){
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getRole()
        );
    }
}