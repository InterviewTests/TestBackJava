## IMPORTANTE
Devido a falta de inteligência minha(Mais conhecida como burrice) acabei fazendo o projeto sem fazer o Fork com o repositório do teste. Então estou publicando apenas o resultado final do teste. Para visualizar todo o histórico de commits por favor acesse o repositório https://github.com/ValdeciJunior/santander
Desculpe-me a burrice.

### PROJETO PARA SELEÇÃO SANTANDER

#### Antes de executar o projeto você precisa:
1. Ter um banco de dados postgres sendo executado localmente na porta 5432(Pode ser via docker)

#### Url para autenticar o usuário e receber o token:
http://localhost:8081/login

#### Json para autenticar
{
	"username": "email@email.com",
	"password": "123"
}
OU
{
	"username": "dev@email.com",
	"password": "123"
}

#### Comando para executar o projeto:
mvn spring-boot:run

#### Utilize o token recebido no Authorization das requisições para poder realiza-las.

#### ENDPOINTS:

1. POST http://localhost:8081/gastos {"valor": 0.00, "descricao":"Descrição do gasto", "data":"01/01/2019 00:00"} obs: Data é opcional, caso ela não seja informada automanticamente será setada a data atual
2. GET http://localhost:8081/gastos
3. GET http://localhost:8081/gastos/{uuid}
4. GET http://localhost:8081/gastos/por-data?data=01/01/2019
5. GET http://localhost:8081/gastos/alterar-categoria/{uuid}?categoria=categoria do gasto
6. GET http://localhost:8081/gastos/pesquisar-categoria/{categoria}

##### Obs: O projeto tem testes unitários e migration para poder auxiliar nas execuções dos métodos
##### Obs2: O não é necessário passar o codigousuario em nenhuma requisição, ele já é recuperado automaticamente no back-end através das informações advindas do token.
