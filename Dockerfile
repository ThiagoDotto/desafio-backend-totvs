FROM openjdk:17-jdk

COPY target/desafio-backend-totvs-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]
