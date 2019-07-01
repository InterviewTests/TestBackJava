# Santander Way
Gerenciamento de Gastos Way

Aplicação feita com Spring Boot, Spring Data com Banco Postgress (Permissão) e Solr(Gasto), Rest com swagger.

<h1>Subindo Aplicação Completa</h1>

Para rodar a aplicação deve-se criar um banco Postgress(Usuario:posrgres Senha:admin)

Criar um database com nome way

Deve-se também criar um banco Solr 

Entrar na pasta bin do Solr  e rodar o comando 

```
.\solr start
```

Para que o mesmo seja iniciado e então rodar o comando

```
.\solr create -c GastoSolr
```
Assim será adicionado um core chamado GastoSolr

Logo após entrar na pasta do projeto rodar o comando

```
mvn spring-boot:run
```

O flyWay irá criar as tabelas necessárias 

Criará também 3 usuarios

Admin (Senha : admin - Id = 1) - usuario admin

User (Senha : user - Id = 2 ) - Usuário do tipo cliente que pode receber inclusão de gastos

Sys (Senha : sys - Id = 3) - Usuário do tipo sistema que pode incluir gastos 
 
aplicação estará com os endpoints disponíveis em :

```
http://localhost:8080/swagger-ui.html
```

<h1>Testes com Mokito</h1>

Para rodar a classe de testes configurada com as conexão de banco mokadas (Mokito)

Deve-se executar o seguinte comando:

```
mvn clean test -Dtest=br.com.zup.way.gasto.GastoApplicationTests
```

Com isso os testes dos serviços irão rodar sem necessidade de conexão com o banco.

<h1>Testes de Integração</h1>

Deve-se executar o seguinte comando:

```
mvn clean test -Dtest=br.com.zup.way.gasto.GastoIntegrationTests
```

Irá ser criado um banco do Solr em mémoria e configurado alguns registros bases para o teste.

O banco postgres já deve ter sido criado para que o mesmo possa validar as informações de usuário.

