#Projeto para Resource

Implementação do teste da Santander (https://github.com/InterviewTests/TestBackJava).

##Configuração

Para executar o projeto, previamente você deve ter os seguintes itens:
- Java 8 (JDK 1.8)
- Maven 3.x
- MySQL 8.x
- REDIS 5.x

Baixe o projeto através do código:

`git clone https://github.com/gabrieldemery/TestBackJava.git`

Ao ter o projeto em sua máquina, deve executar o Maven para baixar as dependências.

`mvn clean install -U`

Para configurar os banco de dados, deve ser alterado os dados no arquivo **application.yml**, na pasta **src/main/resources**.

```yaml
server:
  port: ${PORT:8080}
  
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    password: 
    platform: mysql
    url: jdbc:mysql://localhost:3306/testbackjava?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false
    username: root
  jpa:
      database-platform: org.hibernate.dialect.MySQL8Dialect
      generate-ddl: true
      hibernate:
          ddl-auto: update
      show-sql: true
  redis:
    host: localhost
    port: 6374
```

OBS.: O REDIS está usando as configurações básicas padrão.

##Utilização

No projeto existe uma collection do Postman, onde as requisições para utilizam Basic Auth, ou seja, todas deve ser enviada o usuário e senha em seu cabeçalho. Também serve como exemplo de cada um dos end points.

####Sistema

**Usuário:** SISTEMA
**Senha:** 123

**End Points**
- POST http://localhost/gastos - Enviar um gasto para ser cadastrado.

####Usuário

**Usuário:** USUARIO
**Senha:** 123

**End Points**
- GET http://localhost/gastos - Recebe uma lista de gastos, por usuário.
- GET http://localhost/gastos?data={data} - Recebe uma lista de gastos, por usuário e data determinada.
- GET http://localhost/gastos/{codigogasto} - Recebe um gasto, por código informado.
- POST http://localhost/gastos/{codigogast}/categorizar - Envia uma categoria para categorizar um gasto.

##Autor

**Gabriel D'Emery**
falecomigo@gabrieldemery.com
(81) 997.014.505