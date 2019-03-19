FROM java:8-jre-alpine

RUN mkdir -p /service-gateway
ADD target/service-gateway-0.0.1-SNAPSHOT.jar /service-gateway
ADD target/classes/application.properties /service-gateway

WORKDIR /service-gateway

ENTRYPOINT ["java", "-jar", "service-gateway-0.0.1-SNAPSHOT.jar"]