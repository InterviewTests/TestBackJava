# Show me the code

### # DESAFIO:

API REST para Gestão de Gastos!

 - O projeto ainda está incompleto pois realmente não tive tempo habil nenhum para desenvolver o teste.

 - Foram implementadas funcionalidades para busca pela data do gasto, busca de todos os gastos de determinado cartão, inclusão de gasto e atualização da categoria do gasto
 
 - para o teste utilizei Java, Maven, Spring Boot e Cassandra (para o armazenamento de dados)

 - Antes de iniciar os testes rodando a aplicação é necessário criar um keyspace no cassandra com a execução do seguinte CQL:
CREATE KEYSPACE IF NOT EXISTS sample WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};

 - Para executar a aplicação basta compilar a aplicação usando o maven "mvn clean install" e depois de compilado basta rodar o comando "mvn spring-boot:run".
 
 - A aplicação possui alguns testes unitarios do cassandra, caso queira ignorar os testes ao compilar, pode executar o comando "mvn clean install -DskipTests"
 
 - Os testes SOAPUI encontram-se na raiz do projeto com o nome "REST-Test-Santander-soapui-project.xml", basta importar o projeto no SOAPUI.
 
 - Obs.: O projeto está configurado para rodar na porta 8080, certifique-se de que esta porta esteja livre.
 
 - Obs. 2: Eu queria ter coberto 100% o projeto com testes unitários, más como meu tempo livre é bem curto só está implementado o suficiente para demontrar algumas utilizações de testes.  