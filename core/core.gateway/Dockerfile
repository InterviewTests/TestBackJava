FROM java:8-jre-alpine

RUN mkdir -p /opt/gateway

ADD target/core.gateway-0.0.1-SNAPSHOT.jar opt/gateway
ADD target/classes/ssl-server.jks opt/gateway
ADD target/classes/bootstrap.yml opt/gateway

WORKDIR /opt/gateway

ENTRYPOINT ["java", "-jar", "core.gateway-0.0.1-SNAPSHOT.jar"]
