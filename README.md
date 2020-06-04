# Teste Java Back-end

## Dados do candidato
**Nome:** Dennis Ryudi Takano  
**Empresa emcaminhadora:** Altran  

## Execução do projeto
O projeto Spring Boot é inicializado através da execução do método main localizado em Aplicacao.java, classe localizada no pacote Test.BackJava  
**Versão do Java utilizada:**: JavaSE-1.8 (jre1.8.0_241)  
**OBS:** O banco de dados interno está pré-definido com alguns gastos para facilitar os testes. Os dados desses gastos pode ser consultado no log do programa logo após a inicialização.  

## Funcionalidades

### Integração de gastos
**Descrição:** Inclui um novo gasto para qualquer usuário. Os dados do gasto devem estar em formato JSON no corpo da requisição.  
**Método:** POST  
**URL:** http://localhost:8080/integracao  
**Body (Parâmetros de exemplo):**  
{  
&nbsp;&nbsp;&nbsp;&nbsp;"descricao": "Novo gasto",  
&nbsp;&nbsp;&nbsp;&nbsp;"valor": 1.00,  
&nbsp;&nbsp;&nbsp;&nbsp;"codigousuario": 1,  
&nbsp;&nbsp;&nbsp;&nbsp;"data": "2020-06-04 10:10:10"  
}  

### Listagem de gastos
**Descrição:** Lista todos os gastos relacionados a um usuário. O código do usuário deve ser passado por parâmetro no cabeçalho da requisição.  
**Método:** GET  
**URL:** http://localhost:8080/listagem  
**Header (Parâmetros de exemplo):**  
{  
&nbsp;&nbsp;&nbsp;&nbsp;"codigousuario": 1  
}  

### Filtro de gastos
**Descrição:** Lista todos os gastos relacionados a um usuário e a uma data. O código do usuário deve ser passado por parâmetro no cabeçalho da requisição e a data (dia, mês a ano) devem ser passados no endereço.  
**Método:** GET  
**URL (Parâmetros de exemplo):** http://localhost:8080/filtro?dia=3&mes=6&ano=2020  
**Header (Parâmetros de exemplo):**  
{  
&nbsp;&nbsp;&nbsp;&nbsp;"codigousuario": 1  
}  

### Categorização de gastos
**Descrição:** Define a categoria para um gasto de um usuário. O código do usuário deve ser passado por parâmetro no cabeçalho da requisição, o id do gasto e a categoria devem estar em formato JSON no corpo da requisição.  
**Método:** POST  
**URL:** http://localhost:8080/categorizacao  
**Header (Parâmetros de exemplo):**  
{  
&nbsp;&nbsp;&nbsp;&nbsp;"codigousuario": 1  
}  
**Body (Parâmetros de exemplo):**  
{  
&nbsp;&nbsp;&nbsp;&nbsp;"idgasto": 2,  
&nbsp;&nbsp;&nbsp;&nbsp;"categoria": "Alimentacao"  
}  