# Quarkus - Blocking programming approach - MongoDB - With Virtual Threads

This application is built using the [Quarkus Framework v3.9.2](https://quarkus.io/), that exposes an API Rest, that is connected to [MongoDB](https://www.mongodb.com/) database using the Panache's _Repository Pattern_.

## Set Up ⚙

You must have installed on your machine:
* JDK 21 version
* Apache Maven 3.9.6
* Docker

If you already have it installed, you need to [configure](../../../../../setup/) the MongoDB database.

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

## Virtual Threads

In order to activate or deactivate the usage of virtual threads during execution of the application, the following annotation must be added to the resource:
``` java
@RunOnVirtualThread
```