package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Produto;
import com.ecommerce.ecommerce.repository.interfaces.IProdutoRepository;
import com.ecommerce.ecommerce.service.interfaces.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService implements IProdutoService {

    IProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(IProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto cadastrarProduto(Produto produto){
        return produtoRepository.save(produto);
    }
}