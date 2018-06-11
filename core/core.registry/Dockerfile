FROM java:8-jre-alpine

ADD target/core.registry-0.0.1-SNAPSHOT.jar opt/

WORKDIR /opt/

ENTRYPOINT [ "java", "-jar", "core.registry-0.0.1-SNAPSHOT.jar" ]
