FROM java:8-jre-alpine

ADD target/core.gateway-0.0.1-SNAPSHOT.jar opt/
ADD target/classes/ssl-server.jks opt/
ADD target/classes/bootstrap.yml opt/

WORKDIR /opt/

ENTRYPOINT [ "java", "-jar", "core.gateway-0.0.1-SNAPSHOT.jar" ]