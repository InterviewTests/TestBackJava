O projeto foi criado para banco de dados MySQL, mas como usamos JPA pode ser migrado para outro banco.

No application.properties dentro do resources projeto possui as definições de conexão ao banco de dados.
 
Após subir a aplicação pela primeira vez ele irá criar as tabelas via DDL um usuário no branco de dados com o script.
A senha do usuário é critptografada então para acessar deve usar o script abaixo, aqui ele vai cadastrar um usuário e senha 
usuário: teste
senha: 123456789

```
INSERT INTO `bancoteste`.`tb_usuario`
(`str_nm_usuario`,`str_senha`,`str_usuario`)
VALUES ('Nome do Teste','%��2;E8��bM:�ü��s*�g�e��L��4A',
'teste');-- senha: 123456789

INSERT INTO `tb_cartao`
(`dt_bloqueado`,`str_nm_usuario`,`id_usuario`)VALUES (null,'TESTE T. T. TESTE',1);
```

Para realizar o login acesse a URL acessar o controller "http://localhost:8080/login" 
mandando o campos : usuario e senha

Ele irá te retornar um token que irá usar todo momento em toda aplicação para os demais requizições.
Esse token é gerenciado pelo ehcache, e não possui um classe de controle para limpar logo nessa release logo
quem gerencia a existencia deste token será o ehcache.

Eu iria usar o SOLr pelo menos para registrar o token, mas para agilizar o processe de desenvolvimento acabei usando apenas o ehcache.

Separei os controllers em maior numero possivel para não ficarem super alocados e mais fácil de entender 
classificarGasto
controleGastos
filtroGastos
login
	POST execute o login retornando um token para usar posteriormente






