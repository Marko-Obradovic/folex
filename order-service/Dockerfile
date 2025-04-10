FROM maven:3.9 as build

COPY pom.xml .

COPY src ./src

RUN mvn clean package

FROM openjdk:25-bookworm

COPY --from=build /target/order-service-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "order-service-0.0.1-SNAPSHOT.jar", "--port", "8080"]
