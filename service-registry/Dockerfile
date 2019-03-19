FROM java:8-jre-alpine

RUN mkdir -p /service-registry
ADD target/service-registry-0.0.1-SNAPSHOT.jar /service-registry
ADD target/classes/bootstrap.yml /service-registry

WORKDIR /service-registry

ENTRYPOINT ["java", "-jar", "service-registry-0.0.1-SNAPSHOT.jar"]