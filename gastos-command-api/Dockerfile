FROM java:8-jre-alpine

RUN mkdir -p /gastos
ADD target/gastos-command-api0.0.1-SNAPSHOT.jar /gastos
ADD target/classes/application.properties /gastos

WORKDIR /gastos

ENTRYPOINT ["java", "-jar", "gastos-command-api0.0.1-SNAPSHOT.jar"]