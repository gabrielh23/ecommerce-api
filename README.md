<div align="center">

# 🛒 Ecommerce API

RESTful API de e-commerce construída com **Java**, **Spring Boot**, **PostgreSQL**, **Spring Security**, **JWT**, **Flyway**, **MapStruct** e **Docker**.

Projeto desenvolvido com foco em arquitetura backend, modelagem de domínio de e-commerce e construção de portfólio profissional.

![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.0-6DB33F?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-316192?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=jsonwebtokens)
![MapStruct](https://img.shields.io/badge/MapStruct-1.6.3-red?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

</div>

---

# 📖 Sobre

O objetivo deste projeto é simular uma API de um e-commerce real, implementando conceitos e tecnologias utilizados em aplicações modernas de backend.

A aplicação possui autenticação, catálogo de produtos, variantes, controle de estoque e gerenciamento de imagens.

Entre os principais conceitos aplicados estão:

- Arquitetura em camadas
- API REST
- Autenticação e autorização com JWT
- Spring Security
- Persistência com Spring Data JPA
- Relacionamentos entre entidades
- Versionamento do banco com Flyway
- Mapeamento entre entidades e DTOs com MapStruct
- Tratamento global de exceções
- Validação de dados
- Soft delete
- PostgreSQL em container Docker
- Regras de negócio de estoque
- Modelagem de produtos com variantes e SKU

---

# 🚀 Tecnologias

| Tecnologia | Descrição |
|---|---|
| Java 25 | Linguagem principal |
| Spring Boot 4.1.0 | Framework backend |
| Spring Web | Construção da API REST |
| Spring Security | Segurança e controle de acesso |
| JWT | Autenticação stateless |
| Spring Data JPA | Persistência e acesso ao banco |
| Hibernate | Implementação ORM |
| PostgreSQL 16 | Banco de dados relacional |
| Flyway | Versionamento e migrations |
| Docker | Execução do PostgreSQL em container |
| Maven | Gerenciamento de dependências |
| Lombok | Redução de código boilerplate |
| MapStruct | Mapeamento entre entidades e DTOs |
| Bean Validation | Validação de requisições |
| BCrypt | Criptografia de senhas |

---

# 🏗 Arquitetura

```text
Client
  │
  ▼
HTTP / JSON REST API
  │
  ▼
Controllers
  │
  ▼
Services
  │
  ├── Regras de negócio
  ├── Validações
  └── Transações
  │
  ▼
Mappers
  │
  ▼
Repositories
  │
  ▼
Spring Data JPA / Hibernate
  │
  ▼
PostgreSQL
```

A segurança funciona por meio de um filtro JWT executado antes do acesso aos endpoints protegidos.

```text
Request
  │
  ▼
Authorization: Bearer TOKEN
  │
  ▼
JwtFilter
  │
  ▼
Validação do token
  │
  ▼
UserDetailsService
  │
  ▼
SecurityContext
  │
  ▼
Controller
```

---

# 📂 Estrutura do projeto

```text
src/main/java/com/gabriel/ecommerce

├── config
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── enums
├── exception
├── mapper
├── repository
├── security
├── service
│   └── impl
└── EcommerceApiApplication.java
```

---

# 🧩 Modelagem atual

```text
User

Category
  │
  ▼
Product
  │
  ├── ProductVariant
  │       │
  │       ▼
  │    Inventory
  │
  └── ProductImage
```

## Relacionamentos

- Uma categoria pode possuir vários produtos.
- Um produto pertence a uma categoria.
- Um produto pode possuir várias variantes.
- Cada variante possui um SKU único.
- Cada variante pode possuir um único estoque.
- Um produto pode possuir várias imagens.
- Cada produto pode possuir somente uma imagem principal.

---

# 🔐 Segurança

A autenticação é realizada utilizando JWT.

## Fluxo

```text
Cadastro
  ↓
Senha criptografada com BCrypt
  ↓
Login
  ↓
JWT gerado
  ↓
Authorization: Bearer TOKEN
  ↓
JwtFilter
  ↓
Spring Security
  ↓
Endpoint protegido
```

## Rotas públicas

```text
POST /api/users
POST /api/auth/login
```

As demais rotas exigem autenticação.

---

# 📦 Funcionalidades implementadas

## Usuários e autenticação

- Cadastro de usuários
- Login
- Senha criptografada com BCrypt
- Geração de JWT
- Validação de JWT
- Filtro de autenticação
- Rotas públicas e protegidas
- Perfis de usuário
- Integração com `UserDetails`

## Categorias

- Criar categoria
- Listar categorias
- Buscar categoria por ID
- Atualizar categoria
- Gerar slug automaticamente
- Soft delete

## Produtos

- Criar produto
- Listar produtos
- Buscar produto por ID
- Atualizar produto
- Vincular produto a uma categoria
- Gerar slug automaticamente
- Soft delete
- Conversão com MapStruct

## Variantes de produto

- Criar variante
- Listar variantes
- Buscar variante por ID
- Listar variantes por produto
- Atualizar variante
- Soft delete
- SKU único
- Cor
- Tamanho
- Preço por variante

## Estoque

- Criar estoque para uma variante
- Garantir apenas um estoque por variante
- Listar estoques
- Buscar estoque por ID
- Buscar estoque pela variante
- Atualizar quantidade
- Controlar quantidade reservada
- Calcular quantidade disponível
- Impedir quantidade menor que a reservada
- Impedir remoção de estoque com itens reservados

## Imagens de produto

- Cadastrar imagens por URL
- Listar imagens por produto
- Buscar imagem por ID
- Atualizar imagem
- Excluir imagem
- Ordenar imagens por posição
- Definir imagem principal
- Garantir apenas uma imagem principal por produto
- Impedir URL duplicada no mesmo produto
- Armazenar texto alternativo para acessibilidade e SEO

## Tratamento de erros

- Exceções customizadas
- Tratamento global com `@RestControllerAdvice`
- Padronização das respostas de erro
- Validação de campos
- Erros de negócio
- Erros de autenticação
- Erros de recurso não encontrado

---

# 🐳 Executando o projeto

## Pré-requisitos

- Java 25
- Docker Desktop
- Git
- IntelliJ IDEA ou outra IDE Java

O Maven pode ser executado pelo Maven Wrapper incluído no projeto.

## Clonar o repositório

```bash
git clone https://github.com/gabrielh23/ecommerce-api.git
```

Entrar na pasta:

```bash
cd ecommerce-api
```

## Subir o PostgreSQL

```bash
docker compose up -d
```

Verifique se o container está ativo:

```bash
docker ps
```

## Executar a aplicação

No PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Ou execute a classe:

```text
EcommerceApiApplication.java
```

A API ficará disponível em:

```text
http://localhost:8080
```

## Compilar o projeto

```powershell
.\mvnw.cmd clean compile
```

---

# 🗄 Banco de dados

| Campo | Valor |
|---|---|
| Host | localhost |
| Porta | 5432 |
| Database | ecommerce_db |
| Usuário | ecommerce_user |
| Senha | ecommerce_password |

## Recriar o banco local

Este comando apaga o volume e todos os dados:

```bash
docker compose down -v
```

Depois:

```bash
docker compose up -d
```

---

# 📚 Endpoints

Todas as rotas protegidas devem receber:

```http
Authorization: Bearer SEU_TOKEN
```

## Usuários

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/users` | Cadastrar usuário |

## Autenticação

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/auth/login` | Fazer login e gerar token |

## Categorias

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/categories` | Criar categoria |
| GET | `/api/categories` | Listar categorias |
| GET | `/api/categories/{id}` | Buscar categoria |
| PUT | `/api/categories/{id}` | Atualizar categoria |
| DELETE | `/api/categories/{id}` | Desativar categoria |

## Produtos

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/products` | Criar produto |
| GET | `/api/products` | Listar produtos |
| GET | `/api/products/{id}` | Buscar produto |
| PUT | `/api/products/{id}` | Atualizar produto |
| DELETE | `/api/products/{id}` | Desativar produto |

## Variantes

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/product-variants` | Criar variante |
| GET | `/api/product-variants` | Listar variantes |
| GET | `/api/product-variants?productId={id}` | Listar por produto |
| GET | `/api/product-variants/{id}` | Buscar variante |
| PUT | `/api/product-variants/{id}` | Atualizar variante |
| DELETE | `/api/product-variants/{id}` | Desativar variante |

## Estoques

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/inventories` | Criar estoque |
| GET | `/api/inventories` | Listar estoques |
| GET | `/api/inventories/{id}` | Buscar estoque |
| GET | `/api/inventories/variant/{productVariantId}` | Buscar pela variante |
| PUT | `/api/inventories/{id}` | Atualizar quantidade |
| DELETE | `/api/inventories/{id}` | Excluir estoque |

## Imagens

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/product-images` | Criar imagem |
| GET | `/api/product-images?productId={id}` | Listar imagens do produto |
| GET | `/api/product-images/{id}` | Buscar imagem |
| PUT | `/api/product-images/{id}` | Atualizar imagem |
| DELETE | `/api/product-images/{id}` | Excluir imagem |

---

# 🧪 Exemplos de requisição

## Cadastro

```http
POST /api/users
```

```json
{
  "name": "Gabriel Henrique",
  "email": "gabriel@email.com",
  "password": "123456"
}
```

## Login

```http
POST /api/auth/login
```

```json
{
  "email": "gabriel@email.com",
  "password": "123456"
}
```

## Criar categoria

```json
{
  "name": "Tênis Masculino",
  "description": "Categoria de tênis masculinos"
}
```

## Criar produto

```json
{
  "name": "Tênis Nike Air Max",
  "description": "Tênis masculino confortável para uso diário",
  "price": 399.90,
  "categoryId": "UUID_DA_CATEGORIA"
}
```

## Criar variante

```json
{
  "sku": "NK-AM-BRANCO-40",
  "color": "Branco",
  "size": "40",
  "price": 429.90,
  "productId": "UUID_DO_PRODUTO"
}
```

## Criar estoque

```json
{
  "productVariantId": "UUID_DA_VARIANTE",
  "quantity": 20
}
```

## Criar imagem

```json
{
  "url": "https://images.example.com/nike-air-max-01.jpg",
  "altText": "Tênis Nike Air Max branco visto de frente",
  "position": 0,
  "mainImage": true,
  "productId": "UUID_DO_PRODUTO"
}
```

---

# 📜 Versionamento do banco

O projeto utiliza Flyway para controlar a evolução do banco.

```text
V1__create_users_table.sql
V2__add_role_to_users_table.sql
V3__create_categories_table.sql
V4__create_products_table.sql
V5__create_product_variants_table.sql
V6__create_inventories_table.sql
V7__create_product_images_table.sql
```

Migrations já executadas não devem ser alteradas. Toda mudança nova deve ser criada em um novo arquivo versionado.

---

# 🗺 Roadmap

## Autenticação

- [x] Cadastro
- [x] Login
- [x] BCrypt
- [x] JWT
- [x] Spring Security
- [x] Perfis de usuário

## Catálogo

- [x] Categorias
- [x] Produtos
- [x] Variantes
- [x] SKU
- [x] Estoque
- [x] Imagens
- [x] Imagem principal
- [ ] Marcas
- [ ] Busca e filtros
- [ ] Paginação
- [ ] Avaliações

## Compras

- [ ] Carrinho
- [ ] Itens do carrinho
- [ ] Reserva de estoque
- [ ] Lista de desejos
- [ ] Cupons

## Pedidos

- [ ] Pedidos
- [ ] Itens do pedido
- [ ] Endereços
- [ ] Pagamentos
- [ ] Histórico de status

## Infraestrutura e qualidade

- [x] Docker
- [x] PostgreSQL
- [x] Flyway
- [x] MapStruct
- [x] Tratamento global de erros
- [ ] Swagger/OpenAPI
- [ ] Testes unitários
- [ ] Mockito
- [ ] Testcontainers
- [ ] GitHub Actions
- [ ] Dockerfile
- [ ] Deploy

## Frontend

- [ ] Aplicação em React
- [ ] Integração com autenticação
- [ ] Catálogo
- [ ] Carrinho
- [ ] Checkout

---

# 🎯 Próximos objetivos

Os próximos passos planejados são:

1. Documentar a API com Swagger/OpenAPI.
2. Criar testes unitários e de integração.
3. Implementar carrinho de compras.
4. Criar o fluxo de pedidos.
5. Desenvolver um frontend em React consumindo a API.

---

# 👨‍💻 Autor

**Gabriel Henrique**

Front-End Developer • Full Stack Developer

GitHub:

https://github.com/gabrielh23

LinkedIn:

https://linkedin.com/in/gabrielh23

---

<div align="center">

Projeto em desenvolvimento para estudos, portfólio e evolução profissional em Java e Spring Boot.

⭐ Se este projeto foi útil, deixe uma estrela no repositório.

</div>