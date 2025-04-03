package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.exception.ProdutoExistsException;
import com.ecommerce.ecommerce.exception.ProdutoNotFoundException;
import com.ecommerce.ecommerce.infra.exception.GlobalExceptionHandler;
import com.ecommerce.ecommerce.infra.security.SecurityConfigurationsTests;
import com.ecommerce.ecommerce.infra.security.SecurityFilter;
import com.ecommerce.ecommerce.model.Produto;
import com.ecommerce.ecommerce.repository.interfaces.IProdutoRepository;
import com.ecommerce.ecommerce.service.interfaces.IProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@WebMvcTest(ProdutoService.class)
@Import({SecurityConfigurationsTests.class, GlobalExceptionHandler.class})
@ActiveProfiles("test")
public class ProdutoServiceTest{

    @Autowired
    IProdutoService produtoService;

    @MockBean
    IProdutoRepository produtoRepository;

    @MockBean
    private SecurityFilter securityFilter;

    @BeforeEach
    public void setUp() {
        standaloneSetup(this.produtoService, new GlobalExceptionHandler());
    }

    @Test
    @DisplayName("Should successfully create a product")
    public void CadastrarProdutoTest1(){

        Produto produto = new Produto(1, "Produto C", 150.0, 20);

        when(this.produtoRepository.findById(anyInt()))
                .thenReturn(Optional.empty());
        when(this.produtoRepository.save(any(Produto.class))).
                thenReturn(produto);

        Produto produtoSaved = produtoService.cadastrarProduto(produto);

        assertEquals(produto, produtoSaved);

        verify(produtoRepository, times(1)).findById(anyInt());
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    @DisplayName("Should throw exception when trying to create a product that already exists")
    public void CadastrarProdutoTest2(){

        Produto produto = new Produto(1, "Produto C", 150.0, 20);

        when(this.produtoRepository.findById(anyInt()))
            .thenReturn(Optional.of(produto));

        assertThrows(ProdutoExistsException.class, () -> {
            produtoService.cadastrarProduto(produto);
        });

        verify(produtoRepository, times(1)).findById(anyInt());
        verify(produtoRepository, never()).save(any(Produto.class));
    }

    @Test
    @DisplayName("Should successfully get all the products")
    public void VisualizarProdutosTest1(){

        Produto produto1 = new Produto(1, "Produto C", 150.0, 20);
        Produto produto2 = new Produto(2, "Produto D", 10.0, 10);
        Produto produto3 = new Produto(3, "Produto E", 3000.0, 5);

        when(this.produtoRepository.findAll())
                .thenReturn(List.of(produto1, produto2, produto3));

        List<Produto> produtos = produtoService.visualizarProdutos();

        assertEquals(3, produtos.size());
        assertEquals(produto1, produtos.get(0));
        assertEquals(produto2, produtos.get(1));
        assertEquals(produto3, produtos.get(2));
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should get a empty list of products")
    public void VisualizarProdutosTest2(){

        when(this.produtoRepository.findAll())
                .thenReturn(List.of());

        List<Produto> produtos = produtoService.visualizarProdutos();

        assertEquals(0, produtos.size());
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should successfully get the product")
    public void VisualizarProdutoTest1(){

        Produto produto = new Produto(1, "Produto C", 150.0, 20);

        when(this.produtoRepository.findById(anyInt()))
                .thenReturn(Optional.of(produto));

        Produto produtoObtido = produtoService.visualizarProduto(produto.getId());

        assertEquals(produto, produtoObtido);
        verify(produtoRepository, times(1)).findById(anyInt());
    }

    @Test
    @DisplayName("Should throw exception when trying to get a product that do not exists")
    public void VisualizarProdutoTest2(){

        when(produtoRepository.findById(anyInt()))
            .thenReturn(Optional.empty());

        assertThrows(ProdutoNotFoundException.class, () -> {
            produtoService.visualizarProduto(anyInt());
        });

        verify(produtoRepository, times(1)).findById(anyInt());
    }

    
}