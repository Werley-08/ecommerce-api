package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.UsuarioDTO;
import com.ecommerce.ecommerce.model.Usuario;
import com.ecommerce.ecommerce.service.UsuarioService;
import com.ecommerce.ecommerce.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ecommerce.ecommerce.mapper.UsuarioMapper.toDTO;
import static com.ecommerce.ecommerce.mapper.UsuarioMapper.toModel;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController{

    private final IUsuarioService usuarioService;

    @Autowired
    public UsuarioController(IUsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastrar")
    public UsuarioDTO cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return toDTO(usuarioService.cadastrarUsuario(toModel(usuarioDTO)));
    }
}