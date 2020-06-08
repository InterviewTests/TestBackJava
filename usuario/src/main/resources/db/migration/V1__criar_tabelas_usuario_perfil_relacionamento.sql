use db_usuarios;
CREATE TABLE tb_usuario(
	cd_usuario int auto_increment,
    nm_usuario varchar(150) not null,
    cd_acesso varchar(255) not null,
    cd_senha varchar(255) not null,
    dt_created datetime not null,
    constraint pk_usuario
		primary key(cd_usuario),
	constraint uk_acesso
		unique key(cd_acesso));

create table tb_perfil(
	cd_perfil int auto_increment,
    nm_perfil varchar(150) not null,
    constraint pk_perfil
		primary key(cd_perfil),
	constraint uk_perfil
		unique key(nm_perfil));
        
create table usuario_perfil(
	cd_usuario int,
    cd_perfil int,
    constraint fk_usuario
		foreign key(cd_usuario)
			references tb_usuario(cd_usuario),
    constraint fk_perfil
		foreign key(cd_perfil)
			references tb_perfil(cd_perfil));
