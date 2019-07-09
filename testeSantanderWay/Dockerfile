FROM openjdk:8-jdk-alpine
LABEL maintainer="jonatas.santos@zup.com.br"
VOLUME /tmp
COPY target/app-1.0.0.jar /app.jar
COPY target/classes/solr-cores /solr-cores
COPY target/classes/docker-entrypoint.sh /docker-entrypoint.sh
RUN apk --update add zip
RUN apk --update add bash
RUN wget https://archive.apache.org/dist/lucene/solr/8.1.1/solr-8.1.1.zip
RUN unzip solr-8.1.1.zip
RUN cp -R ./solr-cores/* solr-8.1.1/server/solr/
EXPOSE 8080
EXPOSE 8983
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
CMD ["; nohup ./solr-8.1.1/bin/solr start -force &>/dev/null &"]