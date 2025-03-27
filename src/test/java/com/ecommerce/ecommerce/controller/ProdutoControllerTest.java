package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.ProdutoDTO;
import com.ecommerce.ecommerce.exception.ProdutoExistsException;
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

import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
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
    @DisplayName("Should successfully create a product")
    public void CadastrarProdutoTest1() {

        ProdutoDTO produtoDTO = new ProdutoDTO(null, "Produto C", 150.0, 20);
        Produto savedProduto = new Produto(1, "Produto C", 150.0, 20);

        when(this.produtoService.cadastrarProduto(any(Produto.class)))
                .thenReturn(savedProduto);

        given()
                .contentType(ContentType.JSON)
                .body(produtoDTO)

        .when()
                .post("/api/produtos/cadastrar")

        .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(1))
                .body("nome", equalTo("Produto C"))
                .body("preco", equalTo(150.0f))
                .body("quantidadeEmEstoque", equalTo(20));
    }

    @Test
    @DisplayName("Should return 400 when trying to create a product that already exists")
    public void CadastrarProdutoTest2() {

        ProdutoDTO produtoDTO = new ProdutoDTO(1, "Produto C", 150.0, 20);

        when(this.produtoService.cadastrarProduto(any(Produto.class)))
                .thenThrow(new ProdutoExistsException(1));

        given()
                .contentType(ContentType.JSON)
                .body(produtoDTO)

                .when()
                .post("/api/produtos/cadastrar")

                .then()
                .statusCode(HttpStatus.CONFLICT.value())
                .body("message", equalTo("Já existe um produto cadastrado com o id: 1"));
    }

    @Test
    @DisplayName("Should return 400 when trying to create a product with empty body")
    public void CadastrarProdutoTest3() {

        given()
                .contentType(ContentType.JSON)
                .body("")  // Corpo vazio

                .when()
                .post("/api/produtos/cadastrar")

                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should successfully get all the products")
    public void VisualizarProdutosTest1() {

        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(1, "Produto A", 100.0, 10));
        produtos.add(new Produto(2, "Produto B", 200.0, 20));
        produtos.add(new Produto(3, "Produto C", 300.0, 30));

        when(this.produtoService.visualizarProdutos())
                .thenReturn(produtos);

        given()
                .accept(ContentType.JSON)

                .when()
                .get("/api/produtos/visualizarTodos")

                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", hasItems(1, 2, 3))
                .body("nome", hasItems("Produto A", "Produto B", "Produto C"))
                .body("preco", hasItems(100.0f, 200.0f, 300.0f))
                .body("quantidadeEmEstoque", hasItems(10, 20, 30));
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

    @Test
    @DisplayName("Should return 400 when ID is not a valid number")
    public void VisualizarProdutoTest3() {
        given()
                .accept(ContentType.JSON)

        .when()
                .get("/api/produtos/visualizar/{id}", "abc")

        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}