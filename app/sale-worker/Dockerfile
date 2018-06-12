FROM java:8-jre-alpine

RUN mkdir -p /opt/sale-worker

ADD target/sale-worker-0.0.1-SNAPSHOT.jar opt/sale-worker
ADD target/classes/application.properties opt/sale-worker

WORKDIR /opt/sale-worker


ENTRYPOINT ["java", "-jar", "sale-worker-0.0.1-SNAPSHOT.jar"]
