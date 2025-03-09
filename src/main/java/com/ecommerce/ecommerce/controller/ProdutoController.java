package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.ProdutoDTO;
import com.ecommerce.ecommerce.service.interfaces.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ecommerce.ecommerce.mapper.ProdutoMapper.toDTO;
import static com.ecommerce.ecommerce.mapper.ProdutoMapper.toModel;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController{

    private final IProdutoService produtoService;

    @Autowired
    public ProdutoController(IProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @PostMapping("/cadastrar")
    public ProdutoDTO cadastrarProduto(@RequestBody ProdutoDTO produtoDTO){
        return toDTO(produtoService.cadastrarProduto(toModel(produtoDTO)));
    }
}