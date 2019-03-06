FROM java:8-jre-alpine

RUN mkdir -p /auth-service
ADD target/auth-service-0.0.1-SNAPSHOT.jar /auth-service
ADD target/classes/application.properties /auth-service

WORKDIR /auth-service

ENTRYPOINT ["java", "-jar", "auth-service-0.0.1-SNAPSHOT.jar"]