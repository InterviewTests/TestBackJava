FROM java:8

MAINTAINER Cleverson de Melo

EXPOSE 8080 

ADD /target/gastos.jar gastos.jar
ENTRYPOINT ["java","-jar", "gastos.jar"]