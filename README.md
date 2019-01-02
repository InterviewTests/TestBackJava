# API para Gestão de Gastos - Teste Backend Santander

Requisitos necessários para utilização do projeto:

- Java
- Maven
- MongoDB
- Redis

### No diretório infra do projeto contem o docker compose que com ele você conseguirá subir a imagem docker do MongoDB e do Redis


Foi feito dois projetos, um pequeno projeto para autenticação Oauth2 utilizando o spring security e outro projeto que é onde está a API de Gestão de gastos


Modo de Utilizar:

- Subir o MongoDB e o Redis com o docker compose
- Iniciar a aplicação Security (Aplicação que fará a autenticação) - A aplicação vai subir na porta 8081
- Iniciar a aplicação expense-management (APIs para gestão de gastos) - A aplicação vai subir na porta 8080

Endpoints:

### OBS: Antes de fazer qualquer requisição ao projeto de gestão de gastos, deve ser feito a autenticação no projeto security

```
url token: http://localhost:8081/oauth/token
client: client
secret: secret

Após obter o token, utilizar o mesmo nas chamadas dos endpoints do projeto de gestão de gastos
```

```
Nesse endpoint pode ser consultado todos os gastos de determinado cliente
GET http://localhost:8080/spending/1234
```

```
Nesse endpoint pode ser incluido um gasto
POST http://localhost:8080/spending

OBS: Caso ainda não exista nenhum gasto para esse cliente onde a descrição seja igual a do novo gasto, o campo categoria retornado no response será null, caso contrário, será retornado o a categoria encontrada.

REQUST:
{
  "descricao": "RSHOP LTDA",
  "valor": 56.9,
  "codigousuario": 1234,
  "data": "2018-12-25T15:00:00"
}

Response:
[
    {
        "id": "5c2cf2cca6f8ede155114d01",
        "descricao": "RSHOP LTDA",
        "categoria": "Almoço",
        "valor": 23.9,
        "codigousuario": 1234,
        "data": "2018-12-25T15:00:00"
    }
]
```

```
Nesse endpoint pode ser consultado um gasto pelo ID do mesmo
GET http://localhost:8080/spending/filter/5c2cf2cca6f8ede155114d01

Response:
{
    "id": "5c2cf2cca6f8ede155114d01",
    "descricao": "RSHOP LTDA",
    "categoria": "Almoço",
    "valor": 23.9,
    "codigousuario": 1234,
    "data": "2018-12-25T15:00:00"
}
```

```
Nesse endpoint pode ser consultado um gasto pela data e código do cliente
GET http://localhost:8080/spending/1234/filter?data=2018-12-25

Response:
[
  {
      "id": "5c2cf2cca6f8ede155114d01",
      "descricao": "RSHOP LTDA",
      "categoria": "Almoço",
      "valor": 23.9,
      "codigousuario": 1234,
      "data": "2018-12-25T15:00:00"
  }
]
```

```
Nesse endpoint pode ser consultado as categorias, conforme passado algum termo, ele busca as categorias que contem aquele termo na palavra
GET http://localhost:8080/spending/categories?term=Ca

Response:
["Casa", "Casamento", "Carro"]
```

```
Nesse endpoint pode ser incluido a categoria do gasto
PUT http://localhost:8080/spending

Request:

{
    "id": "5c2cf2cca6f8ede155114d01",
    "descricao": "RSHOP LTDA",
    "categoria": "Almoço",
    "valor": 23.9,
    "codigousuario": 1234,
    "data": "2018-12-25T15:00:00"
}

Response:

[
    {
        "id": "5c2cf2cca6f8ede155114d01",
        "descricao": "RSHOP LTDA",
        "categoria": "Almoço",
        "valor": 23.9,
        "codigousuario": 1234,
        "data": "2018-12-24T15:00:00"
    }
]

```



Segue abaixo as instruções originais:

# Show me the code

### # DESAFIO:

API REST para Gestão de Gastos!

```
Funcionalidade: Integração de gastos por cartão
  Apenas sistemas credenciados poderão incluir novos gastos
  É esperado um volume de 100.000 inclusões por segundo
  Os gastos, serão informados atraves do protoloco JSON, seguindo padrão:
    { "descricao": "alfanumerico", "valor": double americano, "codigousuario": numerico, "data": Data dem formato UTC }
```
```
Funcionalidade: Listagem de gastos*
  Dado que acesso como um cliente autenticado que pode visualizar os gastos do cartão
  Quando acesso a interface de listagem de gastos
  Então gostaria de ver meus gastos mais atuais.
 
*Para esta funcionalidade é esperado 2.000 acessos por segundo.
*O cliente espera ver gastos realizados a 5 segundos atrás.
```
```
Funcionalidade: Filtro de gastos
  Dado que acesso como um cliente autenticado
  E acessei a interface de listagem de gastos
  E configure o filtro de data igual a 27/03/1992
  Então gostaria de ver meus gastos apenas deste dia.
```
```
Funcionalidade: Categorização de gastos
  Dado que acesso como um cliente autenticado
  Quando acesso o detalhe de um gasto
  E este não possui uma categoria
  Então devo conseguir incluir uma categoria para este
```
```
Funcionalidade: Sugestão de categoria
  Dado que acesso como um cliente autenticado
  Quando acesso o detalhe do gasto que não possui categoria
  E começo a digitar a categoria que desejo
  Então uma lista de sugestões de categoria deve ser exibida, estas baseadas em categorias já informadas por outro usuários.
```
```
Funcionalidade: Categorização automatica de gasto
  No processo de integração de gastos, a categoria deve ser incluida automaticamente 
  caso a descrição de um gasto seja igual a descrição de qualquer outro gasto já categorizado pelo cliente
  o mesmo deve receber esta categoria no momento da inclusão do mesmo
```
### # Avaliação

Você será avaliado pela usabilidade, por respeitar o design e pela arquitetura da API. 
É esperado que você consiga explicar as decisões que tomou durante o desenvolvimento através de commits.

* Springboot - Java - Maven (preferêncialmente) ([https://projects.spring.io/spring-boot/](https://projects.spring.io/spring-boot/))
* RESTFul ([https://blog.mwaysolutions.com/2014/06/05/10-best-practices-for-better-restful-api/](https://blog.mwaysolutions.com/2014/06/05/10-best-practices-for-better-restful-api/))
* DDD ([https://airbrake.io/blog/software-design/domain-driven-design](https://airbrake.io/blog/software-design/domain-driven-design))
* Microservices ([https://martinfowler.com/microservices/](https://martinfowler.com/microservices/))
* Testes unitários, teste o que achar importante (De preferência JUnit + Mockito). Mas pode usar o que você tem mais experiência, só nos explique o que ele tem de bom.
* SOAPUI para testes de carga ([https://www.soapui.org/load-testing/concept.html](https://www.soapui.org/load-testing/concept.html))
* Uso de diferentes formas de armazenamento de dados (REDIS, Cassandra, Solr/Lucene)
* Uso do git
* Diferencial: Criptografia de comunicação, com troca de chaves. ([http://noiseprotocol.org/](http://noiseprotocol.org/))
* Diferencial: CQRS ([https://martinfowler.com/bliki/CQRS.html](https://martinfowler.com/bliki/CQRS.html)) 
* Diferencial: Docker File + Docker Compose (com dbs) para rodar seus jars.

### # Observações gerais

Adicione um arquivo [README.md](http://README.md) com os procedimentos para executar o projeto.
Pedimos que trabalhe sozinho e não divulgue o resultado na internet.

Faça um fork desse desse repositório em seu Github e nos envie um Pull Request com o resultado, por favor informe por qual empresa você esta se candidatando.

### # Importante: não há prazo de entrega, faça com qualidade!

# BOA SORTE!
