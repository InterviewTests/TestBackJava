# Show me the code

### # DESAFIO:

API REST para Gestão de Gastos!

 - O projeto ainda está bem incompleto pois realmente não tive tempo habil nenhum para desenvolver o teste.

 - Foram implementadas poucas funcionalidades
 
 - para o teste utilizei Java, Maven, Spring Boot e Cassandra (para o armazenamento de dados)

 - Antes de iniciar os testes rodando a aplicação é necessário criar um keyspace no cassandra com a execução do seguinte CQL:
CREATE KEYSPACE IF NOT EXISTS sample WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};

 - Para executar a aplicação basta compilar a aplicação usando o maven "mvn clean install".
 
 - A aplicação possui alguns testes unitarios do cassandra, caso queira ignorar os testes ao compilar, pode executar o comando "mvn clean install -DskipTests"
 
 - Após compilada a aplicação basta executar como aplicão java a classe /gestao-gastos/src/main/java/br/com/santander/Main.java;
 
 - Os testes SOAPUI encontram-se na raiz do projeto com o nome "REST-Test-Santander-soapui-project.xml", basta importar o projeto no SOAPUI