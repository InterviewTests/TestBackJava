# Show me the code

## Pré-requisitos:

* JDK 8;
* Docker & docker-compose;
* Maven.

## Bibliotecas Utilizadas:

### Produção

* Spring Boot;
* Spring Cloud;
* Zuul - Proxy reverso;
* Eureka - Serviço de descoberta;
* MapStruct - Criação de Mappers por meio de interfaces;
* QueryDSL - Criação de queries fluentes.

## Bancos de dados

* Solr -  armazenamento das categorias;
* MySQL - armazenamento dos dados transacionais. 

### Testes

* Mockito - Criação de Mocks;
* AssertJ - Asserções fluentes;
* RandomBeans - geração de Beans aleatoriamente.

## Como executar:

* Execute o arquivo `build-docker.bat` na pasta raiz para compilar os serviços e gerar a imagem Docker;
* Execute o comando `docker-compose up` para iniciar a aplicação. A API é exposta na porta *8080*

## Checklist

```
✔️ Funcionalidade: Integração de gastos por cartão
```
```
✔️ Funcionalidade: Listagem de gastos
```
```
✔️ Funcionalidade: Filtro de gastos
```
```
✔️ Funcionalidade: Categorização de gastos
```
```
✔️️️️ Funcionalidade: Sugestão de categoria
```
```
❌ Funcionalidade: Categorização automatica de gasto
```
