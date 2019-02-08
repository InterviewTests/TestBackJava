<<<<<<< HEAD
# Api gastos

Para este projeto é necessário utilizar o banco de dados NO SQL mongodb para inclusão, alteração e consulta dos dados.

Para rodar o projeto basta ter o docker e docker compose instalado na maquina e utilizar o seguinte comando para gerar os container com a imagem do projeto e do mongodb:

docker-compose -f docker-compose.yml up -d

<h1> Versões utilizadas </h1>
-Java v1.8<br>
-Framework SpringBoot v2.1.2<br>
-Maven para gerenciar as dependencias do projeto<br>
-Utilização mongorepository para gerenciamento do repositório<br>
-Banco de dados Mongodb v3.4.6
-IDE Eclipse Mars.2 Release (4.5.2)<br>
-Utilizado Oauth2 v2.1.0 com auto configure para autenticação da api

<h1> EndPoints Disponiveis </h1>

<h2>POST</h2>
http://localhost:8080/oauth/token

Utilizar Header Authorization Basic dXN1YXJpb2FwaTpzb2V1c2Vp
Body do tipo form-data com esse parametros:
grant_type: password
username: santander
password: san123

Após efetuar esta autenticação será gerado o token para utilização dos endpoits da api.

<h1> Endpoints da API </h1>

Lembrando que o uso só será liberado após a autenticaçao onde irá retornar o token do tipo Bearer.

Utilizar no header o parametro Authorization: Bearer Token-gerado e Content-Type: "application/json" 

<h2>GET Lista de gastos</h2>
http://localhost:8080/api/gastos/{codigousuario}

<h2>GET Detalhe do gasto</h2>
http://localhost:8080/api/gastos/{codigousuario}/{id}

<h2>POST Criar gasto</h2>
http://localhost:8080/api/gastos/
<p>Body</p>
{
  "descricao": "Descricao",
  "valor": 1500.00,
  "codigousuario": 1,
  "data": "2019-02-08T19:50:00"
}

<h2>PUT alterar gasto</h2>
{
  "descricao": "Descricao",
  "valor": 1500.00,
  "codigousuario": 1,
  "data": "2019-02-08T19:50:00",
  "idCategoria": "5c5cb2b2aa2d1652ae3a17ea",
  "id": "5c5cb2b2aa2d1652ae3a17e9"
}

<h2>GET Sugestão de categorias</h2>
http://localhost:8080/api/categorias/{categoria}

Para este recurso caso digite uma letra como  a por exemplo, irá retornar todas as categorais com a existentes na base, caso digite loja, irá retornar as categorias cujo tenha loja na Base, método não é case sensitive, irá trazer o dado caso esteja maiusculo ou minusculo.

As funcionalidades de atributição automatica da categoria está definido no serviço.

Foram criada todas as funcionalidades solicitadas no teste.

Qualquer dúvida sigo a disposição.

=======
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
>>>>>>> 8be82c72211a7dee780f0a0a5695cddf9065985d
