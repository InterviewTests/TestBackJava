# Show me the code

### # DESAFIO:

API REST para Gestão de Gastos!

Para instalar a aplicação é necessario rodar o comando:
mvn clean install;

Para iniciar a aplicação é necessário rodar o comando:
mvn spring-boot:run

Foi utilizado como banco de dados o MongoDB.

```
Funcionalidade: Integração de gastos por cartão
  Apenas sistemas credenciados poderão incluir novos gastos
  É esperado um volume de 100.000 inclusões por segundo
  Os gastos, serão informados atraves do protoloco JSON, seguindo padrão:
    { "descricao": "alfanumerico", "valor": double americano, "codigousuario": numerico, "data": Data dem formato UTC }
    
    É encontrada no seguinte link da aplicação: http://localhost:8080/gastos/incluir
    
    Json de Exemplo:
    
    {
    	"descricao" : "Extra",
    	"valor" : "180.00",
    	"codigoUsuario" : 123456,
    	"data" : "1992-02-01T09:45:00"
    }
    
    Se a data não for enviada, será utilizada a data corrente.
```
```
Funcionalidade: Listagem de gastos*
  Dado que acesso como um cliente autenticado que pode visualizar os gastos do cartão
  Quando acesso a interface de listagem de gastos
  Então gostaria de ver meus gastos mais atuais.
  
  http://localhost:8080/gastos/listar/123456
 
*Para esta funcionalidade é esperado 2.000 acessos por segundo.
*O cliente espera ver gastos realizados a 5 segundos atrás.
```
```
Funcionalidade: Filtro de gastos
  Dado que acesso como um cliente autenticado
  E acessei a interface de listagem de gastos
  E configure o filtro de data igual a 27/03/1992
  Então gostaria de ver meus gastos apenas deste dia.
  
  http://localhost:8080/gastos/listar/123456/1992-03-05
```
```
Funcionalidade: Categorização de gastos
  Dado que acesso como um cliente autenticado
  Quando acesso o detalhe de um gasto
  E este não possui uma categoria
  Então devo conseguir incluir uma categoria para este
  
  Não implementado.
```
```
Funcionalidade: Sugestão de categoria
  Dado que acesso como um cliente autenticado
  Quando acesso o detalhe do gasto que não possui categoria
  E começo a digitar a categoria que desejo
  Então uma lista de sugestões de categoria deve ser exibida, estas baseadas em categorias já informadas por outro usuários.
  
  Não implementado.
```
```
Funcionalidade: Categorização automatica de gasto
  No processo de integração de gastos, a categoria deve ser incluida automaticamente 
  caso a descrição de um gasto seja igual a descrição de qualquer outro gasto já categorizado pelo cliente
  o mesmo deve receber esta categoria no momento da inclusão do mesmo
  
  Não implementado.
```