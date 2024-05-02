#Step 1: compilación
FROM maven:3.9.6-eclipse-temurin-21 as builder
WORKDIR /app
COPY ./pom.xml /app/
RUN mvn -B clean verify --fail-never
COPY ./src /app/src
RUN mvn -B package -DskipTests

#Step 2: Dockerización
FROM eclipse-temurin:21-jdk
WORKDIR /usr/src/app/
COPY --from=builder /app/target/quarkus-app/*.jar /usr/src/app/
EXPOSE 8084
CMD [ "java", "-jar", "quarkus-run.jar" ]