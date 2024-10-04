# Book Lend

## Sobre

Book Lend trata-se de uma API para gerenciamento de empréstimos de livros. Conta com autenticação e autorização,
onde usuários comuns conseguem visualizar os livros e realizar empréstimos enquanto administradores tem controle
sobre o gerenciamento completo do sistema.

## Pré-requisitos

Lista das ferramentas necessárias para instalação e execução do projeto:
- Git
- Java 17
- Maven
- Docker
    - Docker Compose
    - Imagem do PostgreSQL

## Instalação e Execução

1. Clone o repositório

```bash
git clone https://github.com/martingarrote/book-lend.git
```

2. Acesse o diretório do projeto

```bash
cd book-lend
```

3. Prontifique o container Docker do PostgreSQL

```bash
docker-compose -f docker/docker-compose.yml up -d
```

4. Configure as dependências do projeto

```bash
mvn clean install
```

5. Execute a aplicação

```bash
mvn spring-boot:run
```

## Objetivos

A realização deste projeto surge em resposta ao estudo teórico que foi realizado sobre diversos assuntos, em especial para aplicar autenticação e autorização com Spring Security, para avançar em outros conhecimentos anteriores e também para obtenção de novos com o surgimento de problemas e dúvidas durante o desenvolvimento.

Tem como ideia central prover uma API utilizável para um sistema de gestão de empréstimos de livros, deste modo, tem como objetivo dispor das principais funcionalidades necessárias para isso, destacam-se:
- Criação de conta e login;
- Listagem e filtragem de livros, com uso de paginação;
- Realização de empréstimos;
- Listagem de empréstimos com filtragem e paginação.

## Visão Geral do Projeto

### Tecnologias Utilizadas

- Java 17
- Maven
- Spring Boot
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Spring Validation
- MapStruct
- Flyway
- PostgreSQL
- H2 Database
- Lombok
- Spring Boot DevTools

