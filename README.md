Desafio Gestão de Gastos (Processo seletivo GFT)

Para rodar aplicação é necessário subir os serviços na seguinte ordem: 

Na IDE STS, Eclipse ou IntelliJ.

1° Subir o serviço do Eureka -> Serice Discovery registra todos os serviços da aplicação
	No pacote package br.com.microservice.eureka;
	Executar a Class EurekaApplication no RUN AS Spring Boot APP
	
2° Subir o serviço do Zuul -> API Gateway
	No pacote package br.com.microservice.zuul;
	Executar a Class ZuulApplication no RUN AS Spring Boot APP

3° Subir o serviço Auth -> Serviço de autentaticação de usuário com TOKEN
	No pacote package br.com.microservice.auth;
	Executar a Class AuthApplication no RUN AS Spring Boot APP
	
4° Subir o serviço Cliente
	No pacote package br.com.gft.clientes;
	Executar a Class ClientesApplication no RUN AS Spring Boot APP
	
4° Subir o serviço Gastos
	No pacote package br.com.gft.gastos;
	Executar a Class GastosApplication no RUN AS Spring Boot APP
	
Os teste realizados com o Postman.

Exemplos de end points na pasta /endpoint

Banco de Dados:

O banco de dados utilizado foi o H2 em memória.

Observação: os dados são removidos quando a aplicação é reiniciada.

Segue links da interface dos bancos:

Gastos:
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:file:~/test

Clientes:
http://localhost:8081/h2-console
JDBC URL: jdbc:h2:file:~/teste

Para todos o request para a aplicação é necessário passar o token:

http://localhost:8088/oauth/token

Passarr no body form/data 

scope - web
grant_type - password
username - bruno 
password - pwd

Passar no Authorization 

usename - cliente
password - clientepwd

Autenticar user: 

http://localhost:8088/user

Passar o Token nas requisições:

Authorization OAuth 2.0 incluir o token gerado

Para cada serviço foi criado um repositório no GitHub segue links para maiores informações:

Auth:
https://github.com/BrunoRFernandes/desafio-gestao-de-gastos-auth

Clientes:
https://github.com/BrunoRFernandes/desafio-gestao-de-gastos-clientes
	
Eureka: 
https://github.com/BrunoRFernandes/desafio-gestao-de-gastos-eureka

Gastos:
https://github.com/BrunoRFernandes/desafio-gestao-de-gastos-zuul

Zuul:
https://github.com/BrunoRFernandes/desafio-gestao-de-gastos-zuul
