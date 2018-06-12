FROM java:8-jre-alpine

RUN mkdir -p /opt/registry

ADD target/core.registry-0.0.1-SNAPSHOT.jar opt/registry
ADD target/classes/application.properties opt/registry

WORKDIR /opt/registry

ENTRYPOINT ["java", "-jar", "core.registry-0.0.1-SNAPSHOT.jar"]
