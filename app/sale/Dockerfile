FROM java:8-jre-alpine

RUN mkdir -p /opt/sale-api

ADD target/sale-api-0.0.1-SNAPSHOT.jar opt/sale-api
ADD target/classes/application.properties opt/sale-api

WORKDIR /opt/sale-api

ENTRYPOINT ["java", "-jar", "sale-api-0.0.1-SNAPSHOT.jar"]
