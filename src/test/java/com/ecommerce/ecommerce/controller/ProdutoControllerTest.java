package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.exception.ProdutoNotFoundException;
import com.ecommerce.ecommerce.infra.exception.GlobalExceptionHandler;
import com.ecommerce.ecommerce.infra.security.SecurityConfigurationsTests;
import com.ecommerce.ecommerce.infra.security.SecurityFilter;
import com.ecommerce.ecommerce.model.Produto;
import com.ecommerce.ecommerce.service.ProdutoService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;

@WebMvcTest(ProdutoController.class)
@Import({SecurityConfigurationsTests.class, GlobalExceptionHandler.class})
@ActiveProfiles("test")
public class ProdutoControllerTest {

    @Autowired
    private ProdutoController produtoController;

    @MockBean
    private ProdutoService produtoService;

    @MockBean
    private SecurityFilter securityFilter;

    @BeforeEach
    public void setUp() {
        standaloneSetup(this.produtoController, new GlobalExceptionHandler());
    }

    @Test
    @DisplayName("Should get 'produto' when the id is valid")
    public void VisualizarProdutoTest1() {

        when(this.produtoService.visualizarProduto(1))
                .thenReturn(new Produto(1, "Produto A", 100.0, 10));

        given()
                .accept(ContentType.JSON)

        .when()
                .get("/api/produtos/visualizar/{id}", "1")

        .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(1))
                .body("nome", equalTo("Produto A"))
                .body("preco", equalTo(100.0f))
                .body("quantidadeEmEstoque", equalTo(10));
    }

    @Test
    @DisplayName("Should return 404 when the id is invalid")
    public void VisualizarProdutoTest2() {

        when(this.produtoService.visualizarProduto(1))
                .thenThrow(new ProdutoNotFoundException(1));

        given()
                .accept(ContentType.JSON)

        .when()
                .get("/api/produtos/visualizar/{id}", "1")

        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Produto não encontrado com o id: 1"));
    }
}