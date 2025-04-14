FROM maven:3.8.4-openjdk-11-slim AS build

COPY pom.xml .

COPY src ./src
RUN mvn clean package

FROM openjdk:24-ea-21-jdk-slim-bullseye

COPY --from=build target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
