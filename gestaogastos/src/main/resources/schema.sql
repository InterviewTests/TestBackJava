drop table if exists categoria;
create table categoria
(
   id bigint PRIMARY KEY AUTO_INCREMENT,
   nome varchar(255) null
);

drop table if exists gasto;
create table gasto
(
   id bigint PRIMARY KEY AUTO_INCREMENT,
   descricao varchar(255) null,
   valor decimal(10,2) not null,
   codigousuario int not null,
   data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   categoria_id bigint,
   foreign key (categoria_id) references categoria(id)
);
