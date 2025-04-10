# E-commerce API 🛒


Este é um projeto de exemplo de uma API de e-commerce desenvolvida com **Java** e **Spring Boot**.
O objetivo deste repositório é armazenar meus códigos no estudo de diversas funcionalidades e boas práticas no desenvolvimento
de aplicações Spring.

---

### 🚀 Funcões Básicas:

#### Gerenciamento

- **Gerenciamento de produtos**: A API permite cadastrar, listar, atualizar e excluir produtos no catálogo.
- **Gerenciamento de usuários**: A API permite cadastrar usuários normais e administradores, atribuindo perfis de acesso conforme o tipo de usuário.

#### Autenticação

- **Autenticação de usuários**: A API realiza o login utilizando autenticação com tokens JWT.
- **Gerenciamento de usuários**: Administradores podem listar, atualizar ou excluir usuários.
- **Controle de permissões**: Usuários normais têm acesso às funcionalidades de consulta de produtos, enquanto administradores possuem permissões para gerenciar produtos.

---

### 🛠️ Tecnologias Utilizadas 

- **Java**
- **Spring Boot**: Framework para construção da API
- **Spring Data JPA**: acesso ao banco de dados
- **PostgreSQL**: Banco de dados utilizado para armazenar as informações
- **Spring Security**: Implementar autenticação e autorização
- **JUnit & Mockito**: Testes unitários
- **Flyway**: Gerenciar as migrações de banco de dados

---

### 📄 Como Acessar a Documentação da API no Swagger

Com o repositório clonado, rode o spring no terminal da IDE, usando o comando:

    - "./mvnw spring-boot:run"

- Acesse o Swagger: A documentação estará disponível em http://localhost:8080/swagger-ui/index.html.

Se não funcionar, tente re-sincronizar as dependencias do maven, usando o comando:

    - "./mvnw clean install -U"

Depois, rode o spring novamente

### 📃 Como Acessar a Documentação da API no PostMan

Basta acessar o link: https://documenter.getpostman.com/view/42951140/2sB2cPijsM#5c04c07b-ee70-47ac-828b-100d7d963f63

### 📦 Como Executar a API com Docker Compose

Com o repositório clonado, contrua a imagem, usando o comando:

    - "docker-compose build"

Depois, suba o container, usando o comando:

    - "docker-compose up"

- Acesse: O servidor estará disponível em http://localhost:8080.