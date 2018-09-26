FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
COPY build/libs/teste-back-java-0.0.1-SNAPSHOT.jar teste-back-java.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/teste-back-java.jar"]