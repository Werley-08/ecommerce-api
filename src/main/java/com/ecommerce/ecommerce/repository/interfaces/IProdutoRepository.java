package com.ecommerce.ecommerce.repository.interfaces;

import java.util.List;
import com.ecommerce.ecommerce.model.Produto;

public interface IProdutoRepository {
    Produto save(Produto produto);
    List<Produto> findAll();
}