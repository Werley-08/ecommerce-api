package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.exception.ProdutoExistsException;
import com.ecommerce.ecommerce.exception.ProdutoNotFoundException;
import com.ecommerce.ecommerce.exception.ProdutoUpdateException;
import com.ecommerce.ecommerce.model.Produto;
import com.ecommerce.ecommerce.repository.interfaces.IProdutoRepository;
import com.ecommerce.ecommerce.service.interfaces.IProdutoService;
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
    public Produto cadastrarProduto(Produto produto){

        if(produtoRepository.findById(produto.getId()).isPresent()){
            throw new ProdutoExistsException(produto.getId());
        }

        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> visualizarProdutos(){ return produtoRepository.findAll(); }

    @Override
    public Produto visualizarProduto(Integer id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));
    }

    @Override
    public void deletarProduto(Integer id){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));

        produtoRepository.deleteById(id);
    }

    @Override
    public Produto atualizarProduto(Integer id, Produto produtoAtualizado){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));

        if(!produto.getId().equals(produtoAtualizado.getId())) {
            throw new ProdutoUpdateException();
        }

        deletarProduto(produto.getId());
        produtoAtualizado.setId(id);

        return produtoRepository.save(produtoAtualizado);
    }
}