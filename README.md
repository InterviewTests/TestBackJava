# Show me the code

### # DESAFIO:

Instruções para rodar a Api.

0- Primeiramente, ir na pasta raiz do projeto e compile com o maven
    ```./mvnw clean install```
1- Na pasta Targat Clique 2x no TestBackJava-1.jar
2- A api está usando segurança Oauth 2
  Então é necessario fazer um POST primeiro no endpoint:
    ```http://localhost:8000/gasto/oauth/token```
    Como Authorization basic, deve-se passar o username e passaword que nesse caso é "cliente".
    No Body da requisição deve se passar;
      chave       Valor
      client      cliente
      username    admin
      password    admin
      grant_type  password
    na resposta existe um access_token o qual deve ser copiado e passado no header do http para as outras requisições.
3- A api responde os seguinte endPoonts;
    ```
    POST http://localhost:8000/gasto/  --> Para a inclusão de gastos
    GET  http://localhost:8000/gasto/{idUsuario}/listagemGasto e http://localhost:8000/gasto/{idUsuario}/listagemGasto?data=2019-09-20 --> Para consulta de gastos
    PUT  http://localhost:8000/gasto/{idGasto}/categoria --> Altera uma categoria caso ela possa ser alterada
    GET  http://localhost:8000/gasto/categoria/filtro?categoria={caracter} --> lista categorias que iniciam com um caracter
    ```

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
