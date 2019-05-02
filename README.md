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
A inclusão de gastos deve ser feita através do endpoint *POST* _/gastos/cadastrar_ utilizando autenticação *Basic Auth*, utilizando o usuário _credenciado_ e senha _credenciadoPaSS_.
O payload dessa chamada deve ser um Array contendo 1 ou mais gastos seguindo o padrão acima.
_________________________________________________________________________________________________
```
Funcionalidade: Listagem de gastos*
  Dado que acesso como um cliente autenticado que pode visualizar os gastos do cartão
  Quando acesso a interface de listagem de gastos
  Então gostaria de ver meus gastos mais atuais.
 
*Para esta funcionalidade é esperado 2.000 acessos por segundo.
*O cliente espera ver gastos realizados a 5 segundos atrás.
```
É necesário cadastrar os usuários através do endpoint *POST* _/users/new_. Esse endpoint não requer autenticação.
O payload deve ser um JSON seguindo o padrão:
```
{
	"codigousuario":numerico,
	"username":"alfanumerico",
	"password":"alfanumerico"
}
```
Esse usuário será utilizado para fazer a chamada ao endpoint de listagem de gastos, *GET* _gastos/listar_ com autenticação *Basic Auth*.
Serão listados os gastos onde o codigousuario seja igual ao do usuário autenticado.
_________________________________________________________________________________________________
```
Funcionalidade: Filtro de gastos
  Dado que acesso como um cliente autenticado
  E acessei a interface de listagem de gastos
  E configure o filtro de data igual a 27/03/1992
  Então gostaria de ver meus gastos apenas deste dia.
```
O mesmo endpoint da questão anterior pode ser utilizado com o parâmetro _data_ na URL. *GET* _gastos/listar?data=01/05/2019_
_________________________________________________________________________________________________
```
Funcionalidade: Categorização de gastos
  Dado que acesso como um cliente autenticado
  Quando acesso o detalhe de um gasto
  E este não possui uma categoria
  Então devo conseguir incluir uma categoria para este
```
Através do endpoint *GET* _gastos/{id}_ é possível acessar os detalhes de um gasto cadastrado.
O endpoint *POST* _gastos/{id}_ é utilizado para definir a categoria do gasto. Utilizando o payload:
```
{
	"categoria":"alfanumerico"
}
```
_________________________________________________________________________________________________
```
Funcionalidade: Sugestão de categoria
  Dado que acesso como um cliente autenticado
  Quando acesso o detalhe do gasto que não possui categoria
  E começo a digitar a categoria que desejo
  Então uma lista de sugestões de categoria deve ser exibida, estas baseadas em categorias já informadas por outro usuários.
```
O endpoint *POST* _/gastos/categorias_, com o payload *text/plain*, 
irá retornar uma lista, sem repetição e ordenada com as categorias que iniciam com o texto informado.
_________________________________________________________________________________________________
```
Funcionalidade: Categorização automatica de gasto
  No processo de integração de gastos, a categoria deve ser incluida automaticamente 
  caso a descrição de um gasto seja igual a descrição de qualquer outro gasto já categorizado pelo cliente
  o mesmo deve receber esta categoria no momento da inclusão do mesmo
```

Ao fazer a inclusão o sistema faz uma busca se já existe um gasto com a mesma descrição.
Caso não exista nenhuma, nenhuma categoria será definida.
Caso exista mais de uma, será utilizada a categoria do gasto mais recente (maior id).
_________________________________________________________________________________________________
### # DevNotes

* O projeto utiliza o banco de dados MongoDB rodando localmente para persistir os dados.
* Para rodar o projeto basta executar o método main da classe _br.com.brunots.testes.everis.Application_ 
_________________________________________________________________________________________________

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
