INSERT INTO USER(name, email, password, cpf) 
VALUES('Rafael', 'rafael@gmail.com', '$2a$10$mnDScYY5JddnRUG.kbqSK.0tBPu5DW4DQe64liAC0Ztd4x1N3hBfi', '13630757723');

INSERT INTO USER(name, email, password, cpf) 
VALUES('User', 'user@email.com', '$2a$10$mnDScYY5JddnRUG.kbqSK.0tBPu5DW4DQe64liAC0Ztd4x1N3hBfi', '13630757723');

INSERT INTO CATEGORIA(descricao) VALUES ('Transporte');
INSERT INTO CATEGORIA(descricao) VALUES ('Alimentacao');
INSERT INTO CATEGORIA(descricao) VALUES ('Saude');

INSERT INTO GASTO(id_usuario, data, valor, descricao, categoria_id)
VALUES (1, '2019-05-05 18:00:00', 149.99, 'Compras Mensais Extra', 2);

INSERT INTO GASTO(id_usuario, data, valor, descricao, categoria_id)
VALUES (1, '2019-08-04 18:00:00', 29.99, 'Compras Lojas Americanas', 2);

INSERT INTO GASTO(id_usuario, data, valor, descricao, categoria_id)
VALUES (1, '2019-08-04 10:00:00', 333.00, 'Compra Mi Band', 2);

INSERT INTO GASTO(id_usuario, data, valor, descricao, categoria_id)
VALUES (1, '2019-08-04 22:00:00', 80.00, 'Ifood', 2);

INSERT INTO GASTO(id_usuario, data, valor, descricao, categoria_id)
VALUES (1, '2019-08-05 22:00:00', 10.00, 'Ifood', null);

INSERT INTO GASTO(id_usuario, data, valor, descricao, categoria_id)
VALUES (2, '2019-05-05 18:00:00', 149.99, 'Compras Mensais Extra', 2);

INSERT INTO GASTO(id_usuario, data, valor, descricao, categoria_id)
VALUES (2, '2019-08-04 18:00:00', 29.99, 'Compras Lojas Americanas', 2);

INSERT INTO GASTO(id_usuario, data, valor, descricao, categoria_id)
VALUES (2, '2019-08-04 10:00:00', 333.00, 'Compra Mi Band', 2);

INSERT INTO GASTO(id_usuario, data, valor, descricao, categoria_id)
VALUES (2, '2019-08-04 22:00:00', 80.00, 'Ifood', 2);

INSERT INTO GASTO(id_usuario, data, valor, descricao, categoria_id)
VALUES (2, '2019-08-05 22:00:00', 10.00, 'Ifood', 2);