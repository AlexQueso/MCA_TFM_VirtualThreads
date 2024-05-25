# Spring Boot - Blocking programming approach - Mysql

This application is built using the [Spring Boot v3.2.3](https://spring.io/projects/spring-boot), that exposes an API Rest, that is
connected to [Mysql](https://www.mysql.com/) database.

## Set Up ⚙

You must have installed on your machine:

* JDK 21 version
* Apache Maven 3.9.6
* Docker

If you already have it installed, you need to [configure](../../../../../setup/) the Mysql database.

## Start Up 🛠

You can run the application as follows:

```bash
./mvnw spring-boot:run
```

## Virtual Threads

In order to activate or deactivate the usage of virtual threads during execution of the application, the following property must be set to `true` or `false`:
```
spring.threads.virtual.enabled=true
```
