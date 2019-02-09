FROM java:8

MAINTAINER Cleverson de Melo

ADD /target/gastos-v1.jar gastos-v1.jar
ENTRYPOINT ["java","-jar", "gastos-v1.jar"]