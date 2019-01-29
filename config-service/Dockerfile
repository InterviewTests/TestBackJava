FROM java:8-jre-alpine

RUN mkdir -p /config-service
ADD target/config-service-0.0.1-SNAPSHOT.jar /config-service
ADD target/classes/bootstrap.yml /config-service

WORKDIR /config-service

ENTRYPOINT ["java", "-jar", "config-service-0.0.1-SNAPSHOT.jar"]