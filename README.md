# Teste Backend Java

Resolução do teste para a vaga de desenvolvedor Java.

## Requisitos 

Para compilar e rodar o aplicativo você precisa de:

 - [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
 - [Maven](https://maven.apache.org)
 - [Redis](https://redis.io/)
 
## Executando o aplicativo na máquina local

Há várias maneiras de executar um aplicativo Spring Boot na máquina local. Nesse projeto utilizei a IDE Intellij, podendo assim executar o método main da classe br.com.zup.Application. Que vai rodar o aplicativo na porta 8080.

Ou simplesmente:

```
mvn clean install
```

E para rodar o jar:

```
java -jar target/TesteZup-0.1.0.jar
```

## Utilizando a API

Para incluir novo gasto, utilize o comando.

```
curl -H "Content-Type: application/json" -X POST -d "{"""descricao""":"""Descricao do Gasto""","""valor""":1234.12, """codigousuario""":"""1""", """data""":"""2018-04-27T16:20:34Z"""}" http://localhost:8080/incluir
```

Para listar os gastos atuais do usuário.
Ex:
Código de usuário = 1

```
curl http://localhost:8080/gastosAtuais/1
```

Para filtrar os gastos pela data do usuário.
Ex:
Código de usuário = 1
Data = 27/04/1992

```
curl http://localhost:8080/gastos/1?data=27/04/1992
```

Para modificar a categoria de um gasto.
Ex:
Código de usuário = 1
id do gasto = 0
nova categoria = "novaCategoria"

```
curl -X PUT -d id=0 -d categoria=novaCategoria http://localhost:8080/gastos/1
```

### Autor

* **Victor Seiji Okubo**