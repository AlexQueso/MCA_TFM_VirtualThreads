# App variations

This section contains the source code of the different versions of the Java web application used for the performance comparisons. 

## What does the application do?
The web application is a movie management system that exposes an API allowing the following operations:

- Retrieve a movie by its ID.
- Get a paginated list of 20 movies.
- Save a new movie to the system.
- Update movie details.
- Update a movie's rating.
- Delete a movie from the system.

The application communicates with a database where the movies are stored. 

## Explanation of the different versions

The goal was to test the performance of 16 versions of the same web application by using four binary variables, which are:
- Spring Boot or Quarkus framework.
- Mysql or MongoDB databases.
- Blocking or non-bloking programming aproach
- Usage or non-usage of virtual threads.

Quarkus doesn't allow (as for today) to run non-blocking code under virtual threads, so there're two variations that were impossible to create.

Also, Spring boot allows the user if a project run under virtual threads or not by adding a single property. So, the same project can be used for both executions. 
```
spring.threads.virtual.enabled=true
```
In the end, only 10 versions of the same web application were developed. Here´s the list of the versions and the links to each project:
|                                       | Without Virtual Threads                                           | With Virtual Threads |
|---------------------------------------|-------------------------------------------------------------------|----------------------|
| Spring Boot - Blocking - Mysql        | [Go to Project](./blocking/spring_boot/db_relational/)            | |
| Spring Boot - Non Blocking - Mysql    | [Go to Project](./non_blocking/spring_boot/db_relational/)        | |
| Spring Boot - Blocking - MongoDB      | [Go to Project](./blocking/spring_boot/db_non_relational/)        | |
| Spring Boot - Non Blocking - MongoDB  | [Go to Project](./non_blocking/spring_boot/db_non_relational/)    | |
| Quarkus - Blocking - Mysql            | [Go to Project](./blocking/quarkus/db_relational/without_VT/)     | [Go to Project](./blocking/quarkus/db_relational/with_VT/) |
| Quarkus - Non Blocking - Mysql        | [Go to Project](./non_blocking/quarkus/db_relational/)            | |
| Quarkus - Blocking - MongoDB          | [Go to Project](./blocking/quarkus/db_non_relational/without_VT/) | [Go to Project](./blocking/quarkus/db_non_relational/with_VT/) |
| Quarkus - Non Blocking - MongoDB      | [Go to Project](./non_blocking/quarkus/db_non_relational/)        | |




