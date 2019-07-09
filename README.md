# Desafio Santander Way

### # DESAFIO:

API REST para Gestão de Gastos!

### # ETAPAS

Faça o clone do projeto
Execute o comando
~~~~
mvn clean package
~~~~
Depois execute o docker apontando para o dockerfile da aplicação
~~~~
docker build -t .
docker run -d -p 8080:8080 -p 8983:8983  -e SOLR_HOME=/solr-cores -t santanderway:latest 
~~~~
Caso o solr não tenha sido executado com sucesso, entre no bash e execute manualmente com o comando:
~~~~
./solr-8.1.1/bin/solr start -force 
~~~~

Agora você pode navegar pelo contrato da aplicação

### # Link do Swagger
http://localhost:8080/swagger-ui.html

### # Autenticação
Já possui dois usuários criados na aplicação, do qual a senha já está criptografada e guardada no solr

### # Sistema
```
{
    "email":"william2@email.com",
    "senha":"william"
}
```
### # Cliente
```
{
    "email":"william@email.com",
    "senha":"william"
}
```

