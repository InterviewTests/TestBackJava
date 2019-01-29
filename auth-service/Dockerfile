FROM java:8-jre-alpine

RUN mkdir -p /auth-service
ADD target/config-service-0.0.1-SNAPSHOT.jar /auth-service
ADD target/classes/bootstrap.yml /auth-service

WORKDIR /auth-service

ENTRYPOINT ["java", "-jar", "auth-service-0.0.1-SNAPSHOT.jar"]
