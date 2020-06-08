CREATE DATABASE IF NOT EXISTS db_categorias;
use db_categorias;

CREATE TABLE tb_categoria(
    cd_categoria int auto_increment,
    nm_categoria varchar(150) not null,
    constraint pk_categoria
        primary key(cd_categoria),
    constraint uk_categoria
        unique key(nm_categoria));