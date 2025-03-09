package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.ProdutoDTO;
import com.ecommerce.ecommerce.model.Produto;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import java.util.List;

@Component
public interface ProdutoMapper{

    static ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidadeEmEstoque());
    }

    static List<ProdutoDTO> toDTO(List<Produto> produtos) {
        return produtos.stream().
                map(produto -> new ProdutoDTO(
                        produto.getId(),
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getQuantidadeEmEstoque()))
                .collect(Collectors.toList());
    }

    static Produto toModel(ProdutoDTO produtoDTO) {
        return new Produto(
                produtoDTO.getId(),
                produtoDTO.getNome(),
                produtoDTO.getPreco(),
                produtoDTO.getQuantidadeEmEstoque());
    }
}