# Instruções para utilização da API

### # CRIAÇÃO DO DATABASE:

create database santanderapi;

create table cliente (

codigo_usuario int(6) unsigned auto_increment, 
nome_cliente varchar(150) not null, 
primary key (codigo_usuario)

);

create table gasto (

id_gasto int(6) unsigned auto_increment, 
descricao varchar(255), 
valor double precision not null, 
codigo_usuario INT(6) UNSIGNED not null,
data_gasto DATETIME, 
primary key (id_gasto),
foreign key (codigo_usuario) references cliente(codigo_usuario)

);

insert into cliente values(1,'Rodrigo');
insert into cliente values(2,'Julio');
insert into cliente values(3,'Roberta');
insert into cliente values(4,'Daniel');
insert into cliente values(5,'Larissa');

insert into gasto values(1,'Carro',220.50,1,'2018-04-29 12:42:17');
insert into gasto values(2,'Casa',280.57,1,'2018-04-30 19:55:12');
insert into gasto values(3,'Cadeira',314.80,1,'2018-12-07 14:51:28');

insert into gasto values(4,'Lustre',80.20,2,'2018-05-21 10:17:10');
insert into gasto values(5,'Geladeira',67.10,2,'2018-09-20 19:55:12');
insert into gasto values(6,'TV',150.25,2,'2018-04-29 19:55:12');

insert into gasto values(7,'Mesa',371.50,3,'2018-07-13 02:15:46');
insert into gasto values(8,'Rack',290.30,3,'2018-11-24 11:27:10');
insert into gasto values(9,'Tinta',120.10,3,'2018-06-26 06:28:12');

insert into gasto values(10,'Computador',480.40,4,'2018-01-07 12:51:04');
insert into gasto values(11,'Notebook',249.90,4,'2018-07-15 18:27:39');
insert into gasto values(12,'Mouse',241.80,4,'2018-10-19 08:25:10');

insert into gasto values(13,'Teclado',236.12,5,'2018-12-18 17:18:11');
insert into gasto values(14,'Panela',274.27,5,'2018-08-26 08:10:16');
insert into gasto values(15,'Fogão',148.10,5,'2018-07-20 19:27:08');

```
```

### # BUILD DO PROJETO:

Executar o comando 'mvn clean install' no diretório raiz do projeto.

```
```

### # INICIALIZANDO O SERVIÇO:

Para iniciar o serviço, executar a classe 'SantanderApiApplication.java', localizada no diretório do projeto.

```
```

### # CONSUMINDO AS FUNCIONALIDADES:

```

Funcionalidade: Listagem de gastos

Executar a chamada ao serviço utilizando a seguinte URL: 'http://localhost:8182/gastos/listarTodos'

```

Funcionalidade: Listagem de gastos por Cliente

Executar a chamada ao serviço utilizando a seguinte URL: 'http://localhost:8184/gastos/busca/listarGastos/PARÂMETRO*'

Parâmetro: ID do cliente a ser consultado

Exemplo: 'http://localhost:8184/gastos/busca/listarGastos/3'

```

Funcionalidade: Inclusão de gastos

Executar a chamada ao serviço utilizando os seguintes atributos no Postman: 

Method: POST
URL: 'http://localhost:8184/gastos/cadastro/incluirGasto'

Body: raw / JSON(application/json)

{"id":21,
"descricao":"Teste",
"valor":222.22,
"codigoUsuario":3,
"dataGasto":07292018}

```


