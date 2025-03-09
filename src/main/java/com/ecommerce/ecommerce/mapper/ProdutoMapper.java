package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.ProdutoDTO;
import com.ecommerce.ecommerce.model.Produto;
import org.springframework.stereotype.Component;

@Component
public interface ProdutoMapper{

    static ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidadeEmEstoque());
    }

    static Produto toModel(ProdutoDTO produtoDTO) {
        return new Produto(
                produtoDTO.getId(),
                produtoDTO.getNome(),
                produtoDTO.getPreco(),
                produtoDTO.getQuantidadeEmEstoque());
    }
}