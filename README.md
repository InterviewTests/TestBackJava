# Como executar

# Docker-Compose

Execute as seguintes linhas de comando

    git clone https://github.com/ThadeuFerreira/TestBackJava
    cd TestBackJava
    mvn package
    java -jar target/SantanderTechnologies-0.0.1-SNAPSHOT.jar
    
    docker-compose build
    docker-compose up
    
ATENÇÃO!
É possivel que na primeira vez que seja executado a aplicação java tente se conectar com o banco de dados antes dele estar funcionando.
Nesse caso, tente novamente. Se o erro persistir, execute a aplicação e o banco de dados separadamente, proxima sessão.

# Executando o banco de dados se paradamente

Crie um servidor de banco de dados mysql no localhost e portas padão 3306 com senha de root "password" e nome test
Ou execute o comando a seguir para criar o servidor e banco pelo Docker

    docker run --name mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=test -p 3306:3306 -p 33060:33060 -d mysql:latest

Execute a aplicação

    mvn spring-boot:run

# Como utilizar a API

A autenticação é feita utilizando Bearer Token. Criar usuário não requer autenticação, todas as outras requisicões precisam do Token obtido no login.

- Criar usuário
    

     POST localhost:8080/api/auth/signup
     
     Payload
     {
        "name": "Thadeu Costa",
        "username": "ThadeuCosta",
        "email":"thadeucosta@gmail.com",
        "password":"Secret"
    }
    
- Login



    Post http://localhost:8080/api/auth/signin
    Payload
    {
                "usernameOrEmail":"ThadeuCosta" ,
                "password":"Secret"
    }
    
- Criar novo cartão de crédito


    POST http://localhost:8080/api/user/newCreditCard
    Payload
    {  
        "number":"666 666 666 666",
        "validationCode":"666",
        "clientName":"Thadeu Antonio F Melo",
        "duoDate":"2020-04-21T18:25:43-05:00"
    }
    
- Adicionar gasto em um Cartão


    POST http://localhost:8080/api/spending
    Payload
    {
        "description":"Descrição",
        "amount": 88.10,
        "category": "Roupas",
        "date": "2019-05-21T18:25:43-05:00",
        "creditCardNumber":"666 666 666 666"
    }

    
- Listar total de gastos do Cartão


    GET http://localhost:8080/api/creditcard/details/<numero do cartão>
    
- Listar todos os gastos do usuário

   
    GET http://localhost:8080/api/user/creditCards

- Listar gastos filtrados por dia


    GET http://localhost:8080/api/spending/filterbydate/21052019
    
 - Listar gastos sem category de um Cartão
 

    GET http://localhost:8080/api/spending/nocategory/<numero do cartão>
    
- Editar a categoria de um gasto
     

    POST http://localhost:8080/api/spending/edit
    Payload
    {
	    "spendingId":9,
	    "category":"Games"
    }
    
    

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
