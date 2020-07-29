
##Teste BackAnd Java Santander

# Requerimentos para rodar o projeto

# Instalar Cassandra via Docker
sudo docker run -p 9042:9042 --rm --name cassandra -d cassandra:latest

# Instalar Kafka via Docker-Compose
# Caminho: resources/kafka/docker-compose.yml
sudo docker-compose up -d

# 1. Primeira opção para rodar, sem docker, executar comando abaixo na raiz do projeto
mvn spring-boot:run

# 2. Segunda opção para rodar, com docker, executar comando abaixo na raiz do projeto
# 2.1 Criar imagem docker
docker build -t jabesfelipe/testbackjava .

# 2.2 Executar container, estava domando erro de NoHostAvailableException por causa do Cassandra, como já era madruga, não tive mais tempo olhar, posteriormente vou dar uma caçada e realizo o commit.
docker run -p 8999:8999 jabesfelipe/testbackjava

# Devido ao tempo que a consultoria Altran me deu (3 horas de testes), que foi no dia 28/07/2020 no final tarde, para entregar na manhã seguinte 29/07/2020 as 10:00, fiquei até madruga, para deixar o projeto nas expectativa do cliente, pórem algumas coisas ficou faltando dar um caçada e implementar..

