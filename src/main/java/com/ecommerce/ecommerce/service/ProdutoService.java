package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Produto;
import com.ecommerce.ecommerce.repository.interfaces.IProdutoRepository;
import com.ecommerce.ecommerce.service.interfaces.IProdutoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService implements IProdutoService {

    IProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(IProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto cadastrarProduto(Produto produto){ return produtoRepository.save(produto); }

    @Override
    public List<Produto> visualizarProdutos(){ return produtoRepository.findAll(); }

    @Override
    public Produto visualizarProduto(Integer id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o id: " + id));
    }

    @Override
    public void deletarProduto(Integer id){
        produtoRepository.deleteById(id);
    }
}