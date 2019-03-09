[![Build Status](https://travis-ci.org/adslima/TestBackJava.svg?branch=master)](https://travis-ci.org/adslima/TestBackJava)


Micro serviços com spring boot, eureka, zuul, oauth, RabbitMq, solr e Axon Framework.


microservices architecture

Contém 5 componentes, todos eles são aplicativos implantáveis ​​independentemente.


Service Registry (registro de serviços):

	Este serviço mantém o registro de todos os microservices que foram implantados. 
Foi feito uso do netflix eureka neste projeto.

Service Gateway (roteamento de serviços):

	Pensando na questão do client realizar suas chamadas, diretamente para um único ponto de entrada 
que encaminharia a solicitação para o serviço de back-end apropriado. Com base nessa questão, 
usei o netflix zuul, o configurando para rotear as solicitações especificando rotas.

Auth Service(serviço de autenticação):

	Para acessar qualquer recurso de autenticação é necessário, 
em vez de usar as credenciais do proprietário do recurso para acessar recursos protegidos, o cliente obtém um token de acesso.


Expense Management Command & Expense Management Query

	Com base no que o CQRS propõe, para que separemos a aplicação em modelos diferentes para atualização e exibição, 
que de acordo com o padrão do CQRS estariamos falando dos Comandos e Consultas.
No momento que o usuário realiza um compra com cartão, isso é roteado para o modelo de comando
que por sua vez, irá realizar operações necessárias e informará ao modelo de consulta
para que as novas informações sejam exibidas para o usuário. 
Ainda com o uso do Axon Framework foi possivel a implementação do padrão arquitetural CQRS.

	Para comunicação de nossas aplicações. ocorrerá através de barramento de ventos fazendo uso do RabbitMq. 
Para a persistência dos gastos com cartões, será feito uso do Mysql.
E para indexação das categotorias, será feito uso do SOLR.  


Pré requisito

    - Maven 3
    - Java 8
	- Git
    - Docker/Docker Compose

No Docker serão criados os serviços listados abaixo:

    - Mysql
    - Solr
    - RabbitMq
	
    - Service Registry
	- Auth Service
    - Service Gateway
	
	- Expense Management Command
	- Expense Management Query
	
Preparando ambiente

	Em cada serviço (registry, auth, gateway, command e query)
	
    executar o seguinte commando:
       - mvn clean compile package -DskipTests
   
Executando

	Após esse procedimento, dentro da pasta raiz do projeto executar
	   - docker-compose up --no-start
		

	Ao termino desse processo, executar os seguintes comandos: 
	
	   - docker start mysql solr rabbit
	   - docker start eureka oauth
	   - docker start command query 
	   - docker start zuul
	   

Com tudo rodando, Devemos realizar a solicitação do token para acesso dos serviços;
para isso faremos o seguinte request:

 POST 
	http://localhost:8765/auth-api/oauth/token?grant_type=password&username=demo&password=password

	Authorization: Basic dHJ1c3RlZC1hcHA6cGFzc3dvcmQ=

	Content-Type: application/json

com o token em 'mãos',vVamos realizar um cadastro de gastos com cartão.

POST
	http://localhost:8765/commands/api-command

	{
       "userCode": 12345,
        "description": "RecargaCel",
        "date": "2019-03-01T01:45:55.031",
        "value": 15.00
    }

Para Listarmos os gastos:
	
GET
	http://localhost:8765/queries/api-queries/12345/expense-menagement

Para a listagem por data;

GET
	http://localhost:8765/queries/api-queries/expense-menagement?userCode=12345&date=2019-03-01T00:00:00
	

Para sugestão de categotorias:

GET
	http://localhost:8765/queries/api-queries/"Texto apesquisar"/categories
