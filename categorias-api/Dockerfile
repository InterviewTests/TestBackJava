FROM java:8-jre-alpine

RUN mkdir -p /categorias
ADD target/categorias-api-0.0.1-SNAPSHOT.jar /categorias
ADD target/classes/application.properties /categorias

WORKDIR /categorias

ENTRYPOINT ["java", "-jar", "categorias-api-0.0.1-SNAPSHOT.jar"]

