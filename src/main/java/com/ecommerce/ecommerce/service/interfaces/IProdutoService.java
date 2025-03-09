package com.ecommerce.ecommerce.service.interfaces;

import com.ecommerce.ecommerce.model.Produto;

import java.util.List;

public interface IProdutoService {
    Produto cadastrarProduto(Produto produto);
    List<Produto> visualizarProdutos();
    Produto visualizarProduto(Integer id);
    void deletarProduto(Integer id);
    Produto atualizarProduto(Integer id, Produto produto);
}