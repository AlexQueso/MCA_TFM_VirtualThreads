# Analysis and Comparison of the Performance of Java Web Applications Running on Virtual Threads

The main goal of this project is to analyze the performance impact of using virtual threads in the execution of Java web applications. Specifically, it aims to test the performance differences of using virtual threads in various versions of the same web application with a database access. Fourteen different versions of the application will be compared using the following four variables:

- Use and non-use of virtual threads during execution
- Development framework: Spring Boot and Quarkus
- Blocking and non-blocking programming models
- Relational and non-relational databases


## Main sections of the repository
- [app_variations](/app_variations/) : This section contians the different versions of the web application projects.
- [setup](/setup/) : The steps and technologies used to replicate the environment for the application testing can be found under this section.
- [results](/results) : 
In this section, the results and conclusions obtained after conducting the tests are presented.
- [docs](/docs/) : Additional documentation can be found here.

