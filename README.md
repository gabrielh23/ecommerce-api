<div align="center">

# 🛒 Ecommerce API

RESTful API de E-commerce construída com **Java**, **Spring Boot**, **PostgreSQL**, **JWT** e **Docker**.

Projeto desenvolvido para estudo de arquitetura backend moderna e construção de um portfólio Full Stack.

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-316192?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=jsonwebtokens)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

</div>

---

# 📖 Sobre

O objetivo deste projeto é simular uma API de um e-commerce real, implementando conceitos utilizados em aplicações de produção.

Entre eles:

- Arquitetura em camadas
- REST API
- Autenticação JWT
- Spring Security
- Versionamento do banco com Flyway
- PostgreSQL
- Docker
- Boas práticas de desenvolvimento

---

# 🚀 Tecnologias

| Tecnologia | Descrição |
|------------|-----------|
| Java | Linguagem principal |
| Spring Boot | Framework Backend |
| Spring Security | Autenticação |
| JWT | Autorização |
| Spring Data JPA | Persistência |
| PostgreSQL | Banco de dados |
| Flyway | Migrations |
| Docker | Banco em container |
| Maven | Gerenciamento de dependências |
| Lombok | Redução de código boilerplate |

---

# 🏗 Arquitetura

```
                 Client

                    │

          HTTP / JSON REST API

                    │

            Spring Controllers

                    │

              Service Layer

                    │

             Repository (JPA)

                    │

               PostgreSQL
```

---

# 📂 Estrutura

```
src/main/java/com/gabriel/ecommerce

├── config
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── enums
├── exception
├── repository
├── security
├── service
│   └── impl
└── EcommerceApiApplication
```

---

# 🔐 Segurança

A autenticação é realizada utilizando JWT.

Fluxo:

```
Cadastro

↓

Login

↓

JWT

↓

Authorization: Bearer TOKEN

↓

JwtFilter

↓

Spring Security

↓

Endpoint protegido
```

---

# 📦 Funcionalidades

## Usuários

- ✅ Cadastro
- ✅ Login
- ✅ BCrypt
- ✅ JWT

## Categorias

- ✅ Criar
- ✅ Atualizar
- ✅ Buscar
- ✅ Listar
- ✅ Soft Delete

---

# 🐳 Executando

## Clonar

```bash
git clone https://github.com/gabrielh23/ecommerce-api.git
```

Entrar na pasta

```bash
cd ecommerce-api
```

---

## Subir PostgreSQL

```bash
docker compose up -d
```

---

## Executar

Windows

```bash
./mvnw spring-boot:run
```

ou execute

```
EcommerceApiApplication
```

---

# 🗄 Banco

| Campo | Valor |
|--------|-------|
| Host | localhost |
| Porta | 5432 |
| Database | ecommerce_db |
| Usuário | ecommerce_user |
| Senha | ecommerce_password |

---

# 📚 Endpoints

## Auth

| Método | Endpoint |
|---------|----------|
| POST | /api/users |
| POST | /api/auth/login |

---

## Categories

| Método | Endpoint |
|---------|----------|
| POST | /api/categories |
| GET | /api/categories |
| GET | /api/categories/{id} |
| PUT | /api/categories/{id} |
| DELETE | /api/categories/{id} |

---

# 📜 Versionamento do banco

```
V1__create_users_table.sql

V2__add_role_to_users_table.sql

V3__create_categories_table.sql
```

---

# 📅 Roadmap

## Autenticação

- [x] Cadastro
- [x] Login
- [x] JWT
- [x] Spring Security

## Catálogo

- [x] Categorias
- [ ] Produtos
- [ ] Upload de imagens
- [ ] Estoque

## Compras

- [ ] Carrinho
- [ ] Pedidos
- [ ] Cupons
- [ ] Pagamentos

## Infraestrutura

- [ ] Swagger
- [ ] Testes Unitários
- [ ] Testcontainers
- [ ] GitHub Actions
- [ ] Deploy

---

# 🎯 Próximo objetivo

Construir um frontend em React consumindo esta API para formar um projeto Full Stack completo.

---

# 👨‍💻 Autor

**Gabriel Henrique**

Frontend Developer • Full Stack Developer

GitHub

https://github.com/gabrielh23

LinkedIn

https://linkedin.com/in/gabrielh23

---

<div align="center">

⭐ Se este projeto foi útil, deixe uma estrela no repositório.

</div>