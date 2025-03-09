package com.ecommerce.ecommerce.controller;

import java.util.List;
import com.ecommerce.ecommerce.dto.ProdutoDTO;
import com.ecommerce.ecommerce.service.interfaces.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.ecommerce.ecommerce.mapper.ProdutoMapper.toDTO;
import static com.ecommerce.ecommerce.mapper.ProdutoMapper.toModel;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController{

    private final IProdutoService produtoService;

    @Autowired
    public ProdutoController(IProdutoService produtoService){ this.produtoService = produtoService; }

    @PostMapping("/cadastrar")
    public ProdutoDTO cadastrarProduto(@RequestBody ProdutoDTO produtoDTO){
        return toDTO(produtoService.cadastrarProduto(toModel(produtoDTO)));
    }

    @GetMapping("/visualizarTodos")
    public List<ProdutoDTO> visualizarProdutos(){ return toDTO(produtoService.visualizarProdutos()); }

    @GetMapping("/visualizar/{id}")
    public ProdutoDTO visualizarProduto(@PathVariable Integer id){ return toDTO(produtoService.visualizarProduto(id)); }

    @DeleteMapping("/deletar/{id}")
    public void deletarProduto(@PathVariable Integer id){ produtoService.deletarProduto(id); }

    @PutMapping("/atualizar/{id}")
    public ProdutoDTO atualizarProduto(@PathVariable Integer id, @RequestBody ProdutoDTO produtoDTO){
        return toDTO(produtoService.atualizarProduto(id, toModel(produtoDTO)));
    }
}