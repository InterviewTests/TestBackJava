
### # DESAFIO:

API REST para Gestão de Gastos!

***

#### Configurando o Mongo
```
docker pull mongo
docker run -d -p 27018:27017 mongo
```

#### Executando o projeto
```
mvn install
mvn clean spring-boot:run
```

As variáveis de ambiente estão configuradas no arquivo *application.properties*.
```
mongo.host=localhost
mongo.port=27018
mongo.database=app1
thread.async_core_pool_size=5
thread.async_max_pool_size=50
```

O arquivo *InterviewSantanderJava-soapui-project.xml* contém exemplos de chamadas para este projeto.