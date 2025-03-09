package com.ecommerce.ecommerce.repository.implement;

import com.ecommerce.ecommerce.model.Produto;
import com.ecommerce.ecommerce.repository.ProdutoRepository;
import com.ecommerce.ecommerce.repository.interfaces.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoRepositoryImplement implements IProdutoRepository{

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoRepositoryImplement(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto save(Produto produto){
        return produtoRepository.save(produto);
    }
}