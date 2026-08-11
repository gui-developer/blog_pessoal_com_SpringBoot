# Blog Pessoal

API REST para um blog pessoal, desenvolvida em Java com Spring Boot. Permite o gerenciamento completo de postagens, temas e usuários, com autenticação via JWT.

## 🚀 Deploy

A aplicação está publicada no [Render](https://render.com).

>https://blogpessoal-658y.onrender.com

## 🛠️ Tecnologias

- **Java**
- **Spring Boot**
- **Spring Security** — autenticação e autorização via JWT
- **Spring Data JPA / Hibernate**
- **MySQL** — banco de dados relacional
- **Swagger** — documentação interativa da API
- **Maven** — gerenciador de dependências

## 📋 Funcionalidades

O projeto expõe endpoints REST com CRUD completo para as seguintes entidades:

- **Usuário** — cadastro, login, atualização de dados, geração de token JWT
- **Tema** — categorização das postagens
- **Postagem** — criação, listagem, edição e exclusão de posts, associados a um usuário e a um tema

### Relacionamentos

- Um **Usuário** pode ter várias **Postagens** (1:N)
- Um **Tema** pode ter várias **Postagens** (1:N)

## 🔐 Autenticação

A API utiliza **Spring Security** com tokens **JWT**. Após o login, o token deve ser enviado no header `Authorization` das requisições protegidas:

```
Authorization: Bearer <seu_token_aqui>
```

## ▶️ Como executar localmente

### Pré-requisitos

- Java 17+
- Maven
- MySQL rodando localmente

### Passos

1. Clone o repositório
   ```bash
   git clone https://github.com/gui-developer/blog_pessoal_com_SpringBoot.git
   cd blog_pessoal_com_SpringBoot
   ```

2. Configure o banco de dados no `application.properties` (ou `application.yml`), apontando para sua instância local do MySQL

3. Execute a aplicação
   ```bash
   ./mvnw spring-boot:run
   ```

4. A API estará disponível em `http://localhost:8080`

## 📖 Documentação da API

Com a aplicação rodando, a documentação interativa do Swagger fica disponível em:

```
http://localhost:8080/swagger-ui.html
```

## 🐳 Docker

O projeto inclui um `Dockerfile` para build da imagem:

```bash
docker build -t blog-pessoal .
docker run -p 8080:8080 blog-pessoal
```

## 👤 Autor

**Guilherme (Gui)**
- GitHub: [@gui-developer](https://github.com/gui-developer)
- LinkedIn: [gui-oliveiracode](https://linkedin.com/in/gui-oliveiracode)
