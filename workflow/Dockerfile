FROM maven:3.9 as build

COPY pom.xml .

COPY src ./src
RUN mvn clean package

FROM openjdk:25-bookworm

COPY --from=build target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
