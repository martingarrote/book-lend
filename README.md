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

## Instalação

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
