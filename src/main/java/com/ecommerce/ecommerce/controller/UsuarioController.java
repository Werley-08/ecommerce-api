package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.AuthDTO;
import com.ecommerce.ecommerce.dto.UsuarioDTO;
import com.ecommerce.ecommerce.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/login")
    public ResponseEntity logarUsuario(@RequestBody AuthDTO usuario){
        return usuarioService.logarUsuario(usuario);
    }

    @GetMapping("/visualizarTodos")
    public List<UsuarioDTO> visualizarUsuarios(){
        return toDTO(usuarioService.visualizarUsuarios());
    }

    @GetMapping("/visualizar/{id}")
    public UsuarioDTO visualizarUsuario(@PathVariable Integer id){
        return toDTO(usuarioService.visualizarUsuario(id));
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarUsuario(@PathVariable Integer id){
        usuarioService.deletarUsuario(id);
    }

    @PutMapping("/atualizar/{id}")
    public UsuarioDTO atualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO){
        return toDTO(usuarioService.atualizarUsuario(id, toModel(usuarioDTO)));
    }
}