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
}