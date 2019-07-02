# Gastos

App desenvolvido utilizando Spring boot, Mysql para persistência de dados e Redis para cache de requisições.

## Compilando

Dentro da pasta do projeto execute o comando

```$ ./gradlew clean build```

Será gerado um arquivo .jar dentro do diretório ```/build/libs```.

## Executando

Para executar a aplicação execute o seguinte comando:

```$ java -jar <PATH>/<FILE_NAME>.jar ```

## Executando com Docker

Para executar a aplicação execute o seguinte comando:

```$ docker-compose up --build ```