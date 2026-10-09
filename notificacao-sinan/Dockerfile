## Estagio 1 - build
FROM maven:3.8.8-eclipse-temurin-21-alpine AS builder
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

## Estagio 2 - runtime
FROM bellsoft/liberica-runtime-container:jre-21-cds-slim-musl
WORKDIR /app
COPY --from=builder /app/target/*.jar notificacao-sinan.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "notificacao-sinan.jar"]