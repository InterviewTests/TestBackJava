# TesteBackJava
Projeto criado para o teste de backend Java Altran

[LINK DO REPOSITÓRIO COM COMMITS](https://github.com/PauloMilk/TesteBackJava)

## Tecnlogias usadas:
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Cloud Netflix](https://spring.io/projects/spring-cloud-netflix)
* [MySQL](https://www.mysql.com/)
* [Maven](https://maven.apache.org/)
* [Docker](https://www.docker.com/)
* [Flyway](https://flywaydb.org/)
## Execução pelo Maven:
1. Faça o clone do projeto;
2. Em seu banco de dados MYSQL crie 3 databases(db_usuarios,db_gastos,db_categorias) com o comando SQL 'create database db_usuarios';
3. Com um editor de texto ou IDE, confirme as credenciais de acesso a base pelo jpa e flyway nos projetos(GASTO,AUTH,CATEGORIA e USUARIO);
4. Pelo terminal ou cmd, acesse o diretório do projeto;
5. Entre no diretorio do Eureka(cd ./eureka) e execute o comando ('mvn spring-boot:run');
6. Entre no diretorio do Usuario(cd ./usuario) e execute o comando ('mvn spring-boot:run');
7. Entre no diretorio do Gasto(cd ./gasto) e execute o comando ('mvn spring-boot:run');
8. Entre no diretorio do Auth(cd ./auth) e execute o comando ('mvn spring-boot:run');
9. Entre no diretorio do Categoria(cd ./categoria) e execute o comando ('mvn spring-boot:run');
10. Entre no diretorio do Zuul(cd ./zuul) e execute o comando ('mvn spring-boot:run').
## Execução pelo Docker
1. Faça o clone do projeto;
2. Entre no diretorio do Eureka(cd ./eureka) e execute o comando ('mvn install');
3. Entre no diretorio do Usuario(cd ./usuario) e execute o comando ('mvn install');
4. Entre no diretorio do Gasto(cd ./gasto) e execute o comando ('mvn install');
5. Entre no diretorio do Auth(cd ./auth) e execute o comando ('mvn install');
6. Entre no diretorio do Categoria(cd ./categoria) e execute o comando ('mvn install');
7. Entre no diretorio do Zuul(cd ./zuul) e execute o comando ('mvn install').
8. Dentro do diretório do projeto execute o comando 'docker-compose build';
9. Após o build, execute o comando 'docker-compose up' para travar o terminal com os logs, caso queira executar em modo Detach execute o comando 'docker-compose up -d';
10. Aguarde o carregamento de todos os containers, alguns podem demorar por conta do banco de dados, acompanhe pelo 'docker ps' pelo status.

## Teste
1. Disponibilizado uma collection para postman
## Usuários cadastrados
1. Acesso: paulo senha: 123
2. Acesso: sistema senha: 123
3. Acesso: leandro senha: 123
4. Acesso: sistema2 senha: 123
5. Acesso: bruno senha: 123
## Cliente Basic auth (Geração de token)
1. Username: cliente password: clientepwd
2. Username: loja password: lojapwd
## Portas Docker
1. Projeto Auth: 8090:8090
2. Projeto Eureka: 8761:8761
3. Projeto Categoria: 8081:8081
4. Projeto Gasto: 8082:8082
5. Projeto Usuario: 8083:8083
6. Projeto Zuul: 5000:5000
7. MYSQL DB_GASTOS: 6033:3306
8. MYSQL DB_USUARIOS: 6034:3306
9. MYSQL DB_CATEGORIAS: 6035:3306



