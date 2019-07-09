FROM solr:8.1.1
LABEL maintainer="jonatas.santos@zup.com.br"
COPY target/app-1.0.0.jar /app.jar
COPY --chown=solr target/classes/solr-cores /solr-cores
EXPOSE 8080
EXPOSE 8983
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]