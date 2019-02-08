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

