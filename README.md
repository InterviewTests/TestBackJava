# Desafio de desenvolvimento back end - Santander

Passos para executar a solução:

1) garanta que o computador possui o JDK versão 1.8, Maven versão 3.6.0, MongoDB versão 4.0.4 e git (qualquer versão recente) instalados. Para isso, abra uma janela de seu terminal e execute os comandos

```
java -version
mvn -version
mongod -version
git --version
```

As versões de cada um devem ser impressas na saída padrão.

2) clone este repositório para o seu computador
3) inicie o MongoDB executando o comando `mongod`. Isso impedirá você de utilizar esta janela do terminal, portanto abra uma nova e passe a usá-la, mas deixe a anterior aberta
3) utilizando a nova janela do terminal, navegue até o diretório local onde você clonou este repositório
4) entre no o diretório do microserviço de autenticação, ou `./auth_microservice`
5) execute o comando `mvn spring-boot:run`
6) abra uma terceira janela de seu terminal e, novamente, navegue até o diretório local onde se encontra este repositório
7) entre no diretório do microserviço de gastos, ou `./spend_microservice`, e finalmente, execute o comando `mvn spring-boot:run`
8) desfrute da aplicação!
&nbsp;
&nbsp;
#### Observações
- Os microserviços estão configurados para ocuparem as portas 8080 e 8081, respectivamente.
- Para executar os testes unitários do microserviço de gastos, é preciso que o microserviço de autenticação esteja online
- Este repositório conta com uma collection e um environment do Postman, uma ferramenta muito útil para testar as funcionalidades de APIs. Para utilizá-los, faça o download e instale o Postman em seu computador (caso ainda não o possua intalado) e importe tanto collection quanto environment para o seu ambiente.
&nbsp;
&nbsp;
#### Possíveis otimizações (próximos passos)
1) Para melhorar a velocidade de resposta da rota de inserção de gastos, seria interessante refatorá-la para que ela inserisse os documentos a serem persistidos primeiramente em um sistema de mensageria, ou no próprio Redis (devido a sua velocidade de inserção e consulta). Após isso, workers assíncronos subscritos ao canal de mensagens seriam responsáveis por persistir os dados no banco de dados de fato
2) Outra possível melhoria seria tornar assíncronas as execuções dos métodos dos controllers (no momento, apenas os métodos da camada de serviço estão sendo processados de forma assíncrona). Isto não foi feito pois o uso da classe `HandlerInterceptorAdapter` faz com que duas chamadas de seu método `preHandle()` ocorram a cada requisição assíncrona recebida, e como a lógica de validação de tokens se encontra dentro deste método, o resultado seria que o número de requisições que o microserviço de autentição precisaria responder para o sistema funcionasse normalmente dobraria
3) Containerizar cada um dos microserviços tornaria muito mais simples a tarefa de movê-los de um infraestrutura para outra, escalá-los e administrá-los no geral
&nbsp;

&nbsp;
Por fim, estou me candidando pela **IBM**.
&nbsp;

&nbsp;
#### O texto original do desafio se encontra abaixo
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
