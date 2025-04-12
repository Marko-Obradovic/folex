FROM openjdk:24-ea-21-jdk-slim-bullseye
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]