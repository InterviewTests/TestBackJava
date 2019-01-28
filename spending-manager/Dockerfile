FROM frolvlad/alpine-oraclejdk8:slim
RUN mkdir -p /spending-manager
ADD target/spending-manager-0.0.1-SNAPSHOT.jar /spending-manager
ADD target/classes/application.properties /spending-manager
WORKDIR /spending-manager
ENTRYPOINT ["java", "-jar", "spending-manager-0.0.1-SNAPSHOT.jar"]