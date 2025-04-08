FROM maven:3.9

COPY pom.xml .

COPY src ./src

RUN mvn clean package

FROM openjdk:25

COPY /target/factory-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "factory-0.0.1-SNAPSHOT.jar", "--port", "8080"]