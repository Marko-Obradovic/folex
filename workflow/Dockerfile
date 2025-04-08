FROM maven:3.8.4-openjdk-11-slim AS build

WORKDIR /app
COPY pom.xml .

COPY src ./src
RUN mvn clean package

FROM openjdk:11-jre-slim

RUN apt-get update && \
    apt-get install -y --no-install-recommends curl && \
    rm -rf /var/lib/apt/lists/* && \
    curl -o /wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh && \
    chmod +x /wait-for-it.sh

RUN useradd -r -s /bin/false apprunner && \
    mkdir -p /app && \
    chown -R apprunner:root /app

WORKDIR /app

COPY --from=build --chown=apprunner:root /app/target/*.jar app.jar

RUN echo '#!/bin/sh\n\
exec java -jar app.jar' > /run.sh && \
    chmod +x /run.sh && \
    chown apprunner:root /run.sh

USER apprunner

EXPOSE 8080
ENTRYPOINT ["/wait-for-it.sh", "camundadb:5432", "--", "/run.sh"]