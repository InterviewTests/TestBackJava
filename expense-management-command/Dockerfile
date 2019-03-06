FROM java:8-jre-alpine

RUN mkdir -p /expense-management-command
ADD target/expense-management-command-0.0.1-SNAPSHOT.jar /expense-management-command
ADD target/classes/bootstrap.yml /expense-management-command

WORKDIR /expense-management-command

ENTRYPOINT ["java", "-jar", "expense-management-command-0.0.1-SNAPSHOT.jar"]