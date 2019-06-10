API REST para Gestão de Gastos!

**Procedimentos de execução**

Dentro do diretório gastos-api digitar
- mvn install -Dmaven.test.skip=true

Observação: é necessário o parâmetro -Dmaven.test.skip=true porque alguns testes exigem a conexão com o Solr (que só estará disponível após executar os procedimentos do Docker)

No diretório raiz (TestBackJava) digitar os comandos
- docker-compose build
- docker-compose up -d

Neste ponto um container com projeto e outro com o Solr estará disponível.

Caso queira executar os testes (unitário e integração), basta editar o arquivo application.properties do projeto Spring e modificar o atributo _spring.data.solr.host=http://solrnode:8983/solr_ para _spring.data.solr.host=http://localhost:8983/solr_ e adicionando um novo atributo para porta _port:8081_.

Assim, dentro do diretório gastos-api (fora do container) basta digitar o seguinte comando para executar os testes:
- mvn test
