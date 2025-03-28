package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.UsuarioDTO;
import com.ecommerce.ecommerce.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

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

    static List<UsuarioDTO> toDTO(List<Usuario> usuarios) {
        return usuarios.stream().
                map(usuario -> new UsuarioDTO(
                        usuario.getId(),
                        usuario.getLogin(),
                        usuario.getRole()))
                .collect(Collectors.toList());
    }
}