package com.ecommerce.ecommerce.repository.interfaces;

import com.ecommerce.ecommerce.model.Produto;

import java.util.List;
import java.util.Optional;

public interface IProdutoRepository {
    Produto save(Produto produto);
    List<Produto> findAll();
    Optional<Produto> findById(Integer id);
    void deleteById(Integer id);
}