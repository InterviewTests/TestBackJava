FROM java:8-jre-alpine

RUN mkdir -p /expense-management-query
ADD target/expense-management-query-0.0.1-SNAPSHOT.jar /expense-management-query
ADD target/classes/bootstrap.yml /expense-management-query

WORKDIR /expense-management-query

ENTRYPOINT ["java", "-jar", "expense-management-query-0.0.1-SNAPSHOT.jar"]