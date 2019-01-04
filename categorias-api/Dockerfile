FROM java:8-jre-alpine

RUN mkdir -p /categorias
ADD target/sugestao-categorias-0.0.1-SNAPSHOT.jar /categorias
ADD target/classes/application.properties /categorias

WORKDIR /categorias

ENTRYPOINT ["java", "-jar", "sugestao-categorias-0.0.1-SNAPSHOT.jar"]

