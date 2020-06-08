FROM openjdk:8-jre-alpine
COPY ./gasto/target/*.jar app.jar
RUN chmod +x /app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]
EXPOSE 8082