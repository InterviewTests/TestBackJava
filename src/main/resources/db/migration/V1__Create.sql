CREATE TABLE "usuario"
(
    "id"    SERIAL NOT NULL,
    "nome"  TEXT   NOT NULL,
    "login" TEXT   NOT NULL,
    "senha" TEXT   NOT NULL,
    "tipo"  INTEGER NOT NULL,
    CONSTRAINT usuario_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "perfil"
(
    "id"    SERIAL NOT NULL,
    "nome"  TEXT   NOT NULL,
    CONSTRAINT perfil_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "usuario_perfil"
(
    "usuario_id" SERIAL NOT NULL,
    "perfis_id"  SERIAL   NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES "usuario" (id),
    FOREIGN KEY ("perfis_id") REFERENCES "perfil" (id)
) WITH (
  OIDS=FALSE
);

CREATE TABLE "categoria"
(
    "id"    SERIAL NOT NULL,
    "nome"  TEXT   NOT NULL,
    CONSTRAINT categoria_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

INSERT INTO "perfil" ("nome") VALUES ( 'ROLE_ADMIN');
INSERT INTO "perfil" ("nome") VALUES ( 'ROLE_USER');
INSERT INTO "perfil" ("nome") VALUES ( 'ROLE_SYS');

INSERT INTO "usuario" ("nome", "login", "senha", "tipo") VALUES ( 'Administrador','admin','$2a$10$.ltOnHeuxrc2JLpiG8Ex0.1MT/L2Ah2faemk6DCR.yZAhK6yt2fve',2);
INSERT INTO "usuario" ("nome", "login", "senha", "tipo") VALUES ( 'Usuario','user','$2a$10$oLSlGuWbD43fYrw.7DzkQubr2Q8Ronbnw8r1zpkjTHzJsQ6z5oXPq',1);
INSERT INTO "usuario" ("nome", "login", "senha", "tipo") VALUES ( 'Sistema','sys','$2a$10$opBp5R5Cvje.VC/jwNTuLOucdUbKpue9kEzA5gSdYEYzZ6Eb/M.Cq',2);


INSERT INTO "usuario_perfil" ("usuario_id","perfis_id") VALUES (1,1);
INSERT INTO "usuario_perfil" ("usuario_id","perfis_id") VALUES (2,2);
INSERT INTO "usuario_perfil" ("usuario_id","perfis_id") VALUES (3,3);


