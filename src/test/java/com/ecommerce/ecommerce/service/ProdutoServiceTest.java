package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.ProdutoDTO;
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

import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
}