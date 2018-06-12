FROM java:8-jre-alpine

RUN mkdir -p /opt/client-api

ADD target/client-api-0.0.1-SNAPSHOT.jar opt/client-api
ADD target/classes/application.properties opt/client-api

WORKDIR /opt/client-api

ENTRYPOINT ["java", "-jar", "client-api-0.0.1-SNAPSHOT.jar"]