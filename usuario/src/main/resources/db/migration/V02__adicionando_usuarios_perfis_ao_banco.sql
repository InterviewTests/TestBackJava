insert into tb_usuario values
(1,"paulo","paulo","$2a$10$U/l9eMPp1UTfQX5VQBVQ0OrwSUBr5D5lKuAj9sPsggboXzj6ri4TO","2020-05-06"),
(2,"sistema","sistema","$2a$10$U/l9eMPp1UTfQX5VQBVQ0OrwSUBr5D5lKuAj9sPsggboXzj6ri4TO","2020-05-07"),
(3,"leandro","leandro","$2a$10$U/l9eMPp1UTfQX5VQBVQ0OrwSUBr5D5lKuAj9sPsggboXzj6ri4TO","2020-05-08"),
(4,"sistema2","sistema2","$2a$10$U/l9eMPp1UTfQX5VQBVQ0OrwSUBr5D5lKuAj9sPsggboXzj6ri4TO","2020-05-09"),
(5,"bruno","bruno","$2a$10$U/l9eMPp1UTfQX5VQBVQ0OrwSUBr5D5lKuAj9sPsggboXzj6ri4TO","2020-05-10");

insert into tb_perfil values 
(1, "CADASTRAR_CATEGORIA"), 
(2, "CONSULTAR_CATEGORIA"), 
(3, "CADASTRAR_GASTO"), 
(4, "ATRIBUIR_CATEGORIA"), 
(5, "CONSULTAR_GASTO"), 
(6, "CONSULTAR_USUARIO");

insert into usuario_perfil values
(1,1),
(1,2),
(1,4),
(1,5),

(3,1),
(3,2),
(3,4),
(3,5),

(5,1),
(5,2),
(5,4),
(5,5),

(2,1),
(2,2),
(2,3),
(2,6),

(4,1),
(4,2),
(4,3),
(4,6);
