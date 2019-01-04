FROM java:8-jre-alpine

RUN mkdir -p /gateway
ADD target/gateway-0.0.1-SNAPSHOT.jar /gateway
ADD target/classes/application.properties /gateway

WORKDIR /gateway

ENTRYPOINT ["java", "-jar", "gateway-0.0.1-SNAPSHOT.jar"]

