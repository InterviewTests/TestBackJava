FROM java:8-jre-alpine

RUN mkdir -p /discovery-service
ADD target/discovery-service-0.0.1-SNAPSHOT.jar /discovery-service
ADD target/bootstrap.yml /discovery-service

WORKDIR /discovery-service

ENTRYPOINT ["java", "-jar", "discovery-service-0.0.1-SNAPSHOT.jar"]