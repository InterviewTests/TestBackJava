FROM java:8
EXPOSE 8080 
ADD /target/gestao-gastos.jar gestao-gastos.jar
ENTRYPOINT ["java","-jar", "gestao-gastos.jar"]