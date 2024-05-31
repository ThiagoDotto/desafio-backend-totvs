FROM openjdk:17-jdk

COPY target/desafio-backend-totvs-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java", "-Dspring.profiles.active=docker", "-jar", "/app/app.jar"]
