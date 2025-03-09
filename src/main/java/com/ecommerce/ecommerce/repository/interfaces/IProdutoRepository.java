package com.ecommerce.ecommerce.repository.interfaces;

import java.util.List;
import java.util.Optional;

import com.ecommerce.ecommerce.model.Produto;

public interface IProdutoRepository {
    Produto save(Produto produto);
    List<Produto> findAll();
    Optional<Produto> findById(Integer id);
    void deleteById(Integer id);
}