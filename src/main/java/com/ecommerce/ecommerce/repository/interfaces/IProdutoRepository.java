package com.ecommerce.ecommerce.repository.interfaces;

import com.ecommerce.ecommerce.model.Produto;

public interface IProdutoRepository {
    Produto save(Produto produto);
}