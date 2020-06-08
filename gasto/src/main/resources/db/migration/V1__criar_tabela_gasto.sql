CREATE DATABASE IF NOT EXISTS db_gastos;
use db_gastos;

CREATE TABLE tb_gasto(
    cd_gasto int auto_increment,
    ds_gasto varchar(255) not null,
    vl_gasto double not null,
    cd_categoria int,
    cd_usuario int not null,
    dt_gasto datetime not null,
    nm_categoria varchar(150),
    constraint pk_gasto
        primary key(cd_gasto));