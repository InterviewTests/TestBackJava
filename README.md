# Banco de dados

Esta API usa o Cassandra como base de dados. Deve-se ter uma instância do banco de dados NoSQL rodando e configuradas as variáveis de ambiente **CASSANDRA_HOST** com o IP ou host e **CASSANDRA_PORT** com a porta a ser utilizada.

O arquivo script1.cql é usado para a criação das tabelas Usuario, Gasto e Categoria no mesmo. Deve ser executado via cqlsh ou algum client de banco de dados antes da execução da API

# Execução

A aplicação foi desenvolvida usando Spring Boot, Java 8 e Maven. Para a execução da mesma, deve-se executar o comando ``mvn spring-boot:run`` na pasta raiz ou ainda ``mvn clean package`` e após a criação do **.jar** na pasta ``target``, o comando ``java -jar bweninger-0.0.1-SNAPSHOT.jar App.java``

## Testes

Para o desenvolvimento dos testes foi usado JUnit e Mockito e as classes de serviço e suas lógicas foram os objetivos. Para execução dos mesmos, rodar o comando ``mvn test`` na pasta raiz

## Controle de Acesso

As urls ``/api/usuarios`` e ``api/login`` estão desprotegidas. Para a criação do usuário é necessário uma requisição POST para a primeira, com o seguinte objeto JSON:
```
{
	"nome" : "Bruno Weninger",
	"senha" : "123",
	"repeteSenha" : "123",
	"cpf" : "123.456.789-00"
}
```
A partir deste momento, o usuario criado pode efetuar login, através da segunda url e mais uma requisição POST. O JSON de entrada segue o seguinte padrão:
```
{	
	"senha" : "123",
	"cpf" : "123.456.789-00"
}
```
A resposta desta chamada devolverá um token para o usuário, o qual deve ser passado em todas as chamadas autenticadas no header **Authentication**

## Endpoints

#### /GET /api/gastos?data=yyyy-MM-ddTHH:mmZ
- retorna todos os meus gastos, o filtro de data é opcional
#### /PUT /api/gastos/{:id}
- Atualiza a categoria do gasto id
``` 
{
	"descricao" : "Alimentação"
}
```
#### /POST /api/gastos
- Cadastra diversos gastos para o usuário logado
```
[
	{ "descricao": "Carrefour", "valor": 123.00, "data": "2019-01-14T12:00:00" }
] 
```
#### /GET api/categorias?categoria=XXX
- Retorna sugestão de categoria baseada na string informada.

## Considerações finais 
Agradeço primeiramente a oportunidade de participar do processo e espero que a API seja compatível com o que estão buscando. Qualquer dúvida favor entrar em contato através do email bweninger@outlook.com
