# E-commerce API 🛒

Este é um projeto de exemplo de uma API de e-commerce desenvolvida com **Java** e **Spring Boot**. O objetivo deste repositório é fornecer uma base para estudo de diversas funcionalidades e boas práticas no desenvolvimento de aplicações Spring, incluindo **Spring Security**, **migrations**, **testes**, e **tratamento de exceções**.

## Objetivo 🎯

Este projeto será utilizado como base para estudar e implementar:

- **Spring Security** 🔐: Implementação de autenticação e autorização na aplicação.
- **Migrations** 🗃️: Gerenciamento de alterações no banco de dados utilizando ferramentas como **Flyway**.
- **Testes** 🧪: Escrever testes automatizados com o **Spring Test** para garantir a qualidade da aplicação.
- **Exceções no Spring** ⚠️: Tratamento e personalização de exceções na API, garantindo uma resposta adequada em caso de erro.

## Funcionalidades Básicas ⚙️

A API deve possuir funcionalidades básicas para um sistema de e-commerce, como:

- **Cadastro de produtos** 🛍️: A API permite cadastrar, listar, atualizar e excluir produtos no catálogo. Cada produto possui informações como nome, descrição, preço e quantidade em estoque.

### Endpoints disponíveis 📡

1. **GET /api/products**  
   Retorna uma lista de todos os produtos cadastrados.

2. **POST /api/products**  
   Cria um novo produto no sistema. O corpo da requisição deve conter os dados do produto (nome, descrição, preço, estoque).

3. **PUT /api/products/{id}**  
   Atualiza as informações de um produto existente. Requer o ID do produto na URL e os dados atualizados no corpo da requisição.

4. **DELETE /api/products/{id}**  
   Exclui um produto do sistema com base no seu ID.

## Tecnologias Utilizadas 🛠️

- **Java** ☕
- **Spring Boot**: Framework para construção da API RESTful 🚀
- **Spring Data JPA**: Para acesso ao banco de dados e operações CRUD 📦
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar as informações 🗃️
- **Spring Security**: Para estudar e implementar autenticação e autorização 🔐
- **JUnit & Mockito**: Para escrever testes unitários e de integração 🧪
- **Flyway**: Ferramenta para gerenciar as migrações de banco de dados 🔄