# Quarkus API Rest and Hibernate ORM with Panache's Repository Pattern

This application is built using the [Quarkus Framework](https://quarkus.io/), that exposes an API Rest, which uses [RESTEasy](https://resteasy.dev/) implementation of the JAX-RS specification, that is connected to MongoDb database using the Panache's _Repository Pattern_.

## Set Up ⚙

You must have installed on your machine:
* JDK 21 version
* Apache Maven 3.9.6
* Docker
* Quarkus 3.9.2

If you already have it installed, you need to [configure](https://github.com/AlexQueso/MCA_TFM_VirtualThreads/tree/main/setup) the MongoDb database.

## Start Up 🛠

There are two option:

1. Development

    You can run your application in dev mode:

    ```bash
    ./mvnw clean compile quarkus:dev
    ```

2. Production

    First, the application must be packaged using:

    ```bash
    ./mvnw package
    ```

    It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory. Now, the only thing left to do is to run the application:

    ```bash
    java -jar target/quarkus-app/quarkus-run.jar
    ```

## Testing 🔍

The application has an OpenAPI dependency, providing a Swagger interface to make requests against to the API, click [here](http://localhost:8080/swagger-ui/) to test it.
