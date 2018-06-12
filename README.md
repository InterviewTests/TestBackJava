# Empresa recrutadora: Rersouce It

# Tutorial

## Requisitos

* Git
* Maven
* Docker
* Docker Compose

## Serviços que rodarão no Docker
Serão criados os serviços abaixo:
* Mysql
* Solr
* RabbitMq
* Eureka
* Zuul
* Sale-Api
* Sale-Worker
* Client-Api

### Mysql
    Persistencia da compras realizadas

### Solr
    Indexação de categoria/descrição

    *Justificativa:* Solr é uma das melhores ferramentas de indexação de documento.

### RabbitMq
    As requisições que chegam no rest são encaminhados para a fila do Rabbit com o objetivo de não superlotar o servidor de request, evitando assim que requestes sejam rejeitados.
    Como existe um requisito não funcional referente à 100k de request por segundos, foi pessado nessa nesse esquema.

    *Justificativa:* A chamada do bando diretamente do serviço bloqueará as as outras chamadas, muito mais quando se trata de consulta de categoria e depois escrita em banco. Deixaremos a consulta e persistencia para os *workers*.

### Eureka
    É um serviço desevolvolvido pela Netflix para **service discovery**.

    *Justificativa:* O uso do Zuul, para criar os cluster

### Zuul
    É um proxy reverso também desevolvolvido pela Netflix que atua também como um **load balance**, trabalha junto com o Eureka para descoberta e agreção dos nós. Dando a possibilidad de criar um cluster
    com facilidade.
    Foi implementado o SSL, então do client ao proxy a comuniação é segura, do proxy à aplicação a comunicação **Não** é segura.
    
    *Justificativa:* O sistema terá um volume de 100k de request/seg, dessa forma o sistema terá que está em cluster com um loadbalance.

### Sale-Api
    Rest responsável por receber as requisições referente à integração de gasto de carão e encaminha para o **RabbitMq**

### Sale-Worker
    Worker responsável por receber as mensagens do rabbit e persistir o gasto. É nesse worker onde é feito a consulta no Solr se aquela des já está indexada, cajo verdadeiro será **cacheado** para futuras consultas.

### Client-Api
    Rest que disponibiliza endpoints de consultas de gastos bem como a edição da categoria. Também é disponibilizado um endpoint para autoconplete referente à categoria.

### TODO
    #### Segurança implementado em Spring Security
        * Autenticação baseada e login e senha, para o serviço **Client-Api**
        * Auorização baseada em oauth2 para os sistemas credenciados
        * Jwt persistido no redis com o objetivo de gerenciar os mesmos.
    #### Melhorar o domínio


## Execução do ambiente
  No diretório raiz exite um arquivo **build.sh** com ele podemos fazer o build do código e gerar as imagens e container bem como fazer o deploy no Docker, **MAS OS SERVIÇOS NÃO _INICIAM_, PARA ISSO RODAREMOS OUTROS CÓDIGOS DO DOCKER-COMPOSE**
  
  Esse script foi criado para linux
  ```
    sh build.sh
  ```

  ### Entre as chamadas tem que haver um tempo para o serviço levantar.
 ```
  docker-compose start eureka mysql solr rabbitmq
  docker-compose start zuul
  docker-compose start sale-worker
  docker-compose start sale-api client-api
```


# Cadastrar uma compra
```
curl -X POST \
  https://localhost:8443/sale \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"descricao":"Papelaria",
	"valor":20.52,
	"codigousuario":1200,
	"data":"2018-06-06T01:37:56"
}'
```

# Listar os gastos
```
curl -X GET \
  https://localhost:8443/client-api/{codigo-do-usuario}/sale/ \
  -H 'cache-control: no-cache' \
```

# Auto complete
```
curl -X GET \
  'https://localhost:8443/client-api/categories?q=Texto' \
  -H 'cache-control: no-cache' \
```

# Editar a categoria
```
curl -X PUT \
  https://localhost:8443/client-api/{codigo-do-uauario}/sale/{codigo-da-compra}/Opac \
  -H 'cache-control: no-cache' \
```
