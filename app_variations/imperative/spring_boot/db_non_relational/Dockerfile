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
COPY --from=builder /app/target/*.jar /usr/src/app/
EXPOSE 8082
CMD [ "java", "-jar", "app-0.0.1.jar" ]