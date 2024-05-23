# App variations

This section contains the source code of the different versions of the Java web application used for the performance comparisons. 

### What does the application do?
The web application is a movie management system that exposes an API allowing the following operations:

- Retrieve a movie by its ID.
- Get a paginated list of 20 movies.
- Save a new movie to the system.
- Update movie details.
- Update a movie's rating.
- Delete a movie from the system.

The application communicates with a database where the movies are stored. 

### Explanation of the different versions

The goal was to test the performance of 16 versions of the same web application by using four binary variables, which are:
- Spring Boot or Quarkus framework.
- Mysql or MongoDB databases.
- Blocking or non-bloking programming aproach
- Usage or non-usage of virtual threads.

Quarkus doesn't allow (as for today) to run non-blocking code under virtual threads, so there're two variations that were impossible to create.

Also, Spring boot allows the user if a project run under virtual threads or not by adding a single property. So, the same code can be used for both executions. 
```
spring.threads.virtual.enabled=true
```
In the end, only 10 versions of the same web application were developed.




