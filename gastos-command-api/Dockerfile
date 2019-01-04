FROM java:8-jre-alpine

RUN mkdir -p /gastos
ADD target/integracao-0.0.1-SNAPSHOT.jar /gastos
ADD target/classes/application.properties /gastos

WORKDIR /gastos

ENTRYPOINT ["java", "-jar", "integracao-0.0.1-SNAPSHOT.jar"]