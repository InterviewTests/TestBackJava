# Show me the code

### # Instruções para rodar o Projeto:

Baixar o projeto na máquina local.
No CMD navegar até a pasta do projeto.
rodar o comando mvn spring-boot:run
(É necessário ter o maven instalado no computador).

A Aplicação irá iniciar e já estará pronta para receber requisiões.
(O banco utilizado foi o banco em memória H2 para facilitar o uso. Não é necessária configuração).

Funcionalidade: Login
EndPoint: (POST) /auth:
(Irá retornar o Bearer Token necessário para todas próximas requisições)
```
Exemplo de JSON:
{
	"email": "user@email.com",
	"password": "123456"
}
```

Funcionalidade: Integração de gastos por cartão
EndPoint: (POST) "/gastos":
(Retorna status 200 OK)

```
  Exemplo de JSON: 
[
	{ 
		"descricao": "Novo Gasto", 
		"valor": 999.99,
		"codigousuario": 1,
		"data": "2013-08-12T18:00:00"
	},
	{
		"descricao": "Compras Mensais Extra", 
		"valor": 29.99,
		"codigousuario": 1,
		"data": "1999-08-12T18:00:00"
	}
] 
```

Funcionalidade: Detalhes do Gasto
EndPoint: (GET) "/gastos/{id}":
(Retorna Dados do gasto do usuário autenticado conforme id passado)
```
Não é necessário passar nehum dado no corpo da requisição 
```

Funcionalidade: Listagem de gastos
EndPoint: (GET) "/gastos":
(Retorna JSON com Gastos do Usuário que fez a requisição)
```
Não é necessário passar nehum dado no corpo da requisição
```

Funcionalidade: Filtro de gastos por Data
EndPoint: GET "/gastos?data"
(Retorna JSON com Gastos do Usuário para a data passada)
```
Exemplo de Requisição: "/gastos?data=2019-08-04"
```

Funcionalidade: Categorização de gastos
EndPoint: (POST) "/gastos/{id}" (id do gasto)
(Retorna status 200 OK)
```
  Exemplo de JSON:
{
	"descricao": "Alimentacao"
}
```
Funcionalidade: Sugestão de categoria
EndPoint: (GET) "/categorias/{descricao}" (descricao da categoria. Não é neccesário a descrição completa.)
(Retorna sugestão de categoria)
```
Não é necessário passar nehum dado no corpo da requisição
```

Funcionalidade: Categorização automatica de gasto
```
Está implementada no momento de integração dos gastos
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
