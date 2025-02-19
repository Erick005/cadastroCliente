# 🏦 API REST AGIBANK - Gerenciamento de Clientes

## 📖 Descrição

Esta API RESTful foi desenvolvida para **gerenciar clientes de um banco**, oferecendo funcionalidades como:  
✔ **Cadastro de clientes**  
✔ **Consulta de informações**  
✔ **Atualização de dados**  
✔ **Remoção de clientes**  

A aplicação garante a **validação de dados essenciais** e **tratamento de exceções** para assegurar a integridade das informações e evitar inconsistências.

---

## 🚀 Acessos Rápidos

- 📑 **Swagger (Documentação Interativa da API):**  
  🔗 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)  

- 🗄️ **Banco de Dados Local (H2 Console):**  
  🔗 [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  

---

## 🛠 Tecnologias Utilizadas

A API foi construída utilizando **Spring Boot 3.4.2**, com as seguintes dependências principais:

### 🔹 📌 Estrutura e Desenvolvimento
- **Spring Boot 3.4.2** → Framework para aplicações Java com configuração automática.
- **Spring Web (3.3.4)** → Facilita a criação de APIs RESTful.

### 🔹 📌 Documentação da API
- **Springdoc OpenAPI (2.8.5)** → Geração automática da documentação no **Swagger UI**.

### 🔹 📌 Banco de Dados e Persistência
- **Spring Data JPA** → Abstração para operações com banco de dados.
- **H2 Database** → Banco de dados em memória para testes locais.

### 🔹 📌 Validação e Segurança
- **Spring Validation (3.3.4)** → Validação automática de entrada de dados (exemplo: CPF e e-mail).

### 🔹 📌 Manipulação de JSON
- **Jackson Datatype JSR310 (2.15.0)** → Suporte para serialização e desserialização de tipos Java 8+.

### 🔹 📌 Otimização do Código
- **Lombok (1.18.30)** → Reduz código repetitivo gerando automaticamente getters, setters e construtores.

---
