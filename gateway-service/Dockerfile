FROM java:8-jre-alpine

RUN mkdir -p /gateway-service
ADD target/gateway-service-0.0.1-SNAPSHOT.jar /gateway-service
ADD target/classes/bootstrap.yml /gateway-service

WORKDIR /gateway-service

ENTRYPOINT ["java", "-jar", "gateway-service-0.0.1-SNAPSHOT.jar"]