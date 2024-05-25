# How to set up the environment for testing? 🛠

During this sections, a explanation on how to replicate the testing scenarios used during the project is provided. There are several pieces of software running at the same time, as shown in the following deployment diagram:

![](../results/assets/diagrams/deployment-diagram-load-testing.png)

The deployment of the testing environment can be divided into three individual categories. 

* Deployment of the web application along with the database.
* Deployment of the Grafana and Prometheus instances for the monitoring and visualization of the web application status.
* Execution of Artillery to run the load tests.

## How to deploy the web application and the database

### Prepare the isolated environment

The more isolated the environment where the web application is running, the better. In order to achieve an isolated environment, the web application will run inside a Docker container. 

In order to achieve valid results, where the environment doesn't change between executions, the Docker Engine was limited to 2 CPU cores and 6 GB of RAM usage. To do so, at least in a Windows machine, a `.wslconfig` file has to be added to the `$HOME` directory with the following content:
```
# Settings apply across all Linux distros running on WSL 2
[wsl2]

# Limits VM memory to use no more than 4 GB, this can be set as whole numbers using GB or MB
memory=6GB 

# Sets the VM to use two virtual processors
processors=2
```

The web application's JVM has to be limited also. That is achieved by telling Java the wanted limitations. In this specific case, where we have a dockerized application, the following property was added to the container environment, like it is done [here](./app_db/docker-compose.yaml#L34):
```
JAVA_TOOL_OPTIONS: '-Xms500M -Xmx500M'
```

### How to dockerized the web application?

In order to be able to run the web application inside a docker container, the application must be dockerized first. Two different methods were used, depending on the framework.

#### Spring Boot dockerization

For Spring Boot applications, the following command has to be executed from the root directory of the project:
```bash
docker build -t alexquesada/<image-name>:<version> 
```
Dockerization is achieved by using a multistage Dockerfile located in the root directory of each project.

#### Quarkus dockerization

To dockerized the Quarkus' projects, [Google JIB](https://es.quarkus.io/guides/container-image#jib) was used. To get a Quarkus' project docker image, the following command has to be executed form the root directory:
```bash
./mvnw install -Dquarkus.container-image.build=true
```

### How to dockerized and customized the databases

The databases used for the tests have to contain a set of 1000 entries. For the Mysql database, [this script](./app_db/imdb.sql) is used during container start up stage. For the MongoDB databse, [this script](./app_db/imdb.js) was used.

### Execute the web application and the database

In order to run a version of the web application and it correspondent database, the selected version has to be uncommented from the [docker-compose.yaml file](./app_db/docker-compose.yaml). 
Once only the wanted containers are uncommented. the following command has to be executed from the same directory where the docker-compose.yaml file is:
```bash
docker compose up
```

---

## Configure Grafana and Prometheus

Once the web application has started running, its data can be collected and visualized using Grafana/Prometheus. An instance of each piece of software can be instatiated by using the following [docker-compose.yaml file](./monitoring/docker-compose.yaml) that can be found [here](./monitoring/) and executing the following command from that directory:
```bash
docker compose up
```
After both containers are running, Grafana UI can be accessed in the web browser in this url: http://localhost:3000/

Inside the Grafana UI, first, login using the credentials adming/admin. Then, in the right up corner, and try to import a dashboard. The dashboard used for this project was the one with the Id [4701](https://grafana.com/grafana/dashboards/4701-jvm-micrometer/). Prometheus has to be selected as the data source.

## Run the Artillery load tests

At this point, the web application and the database should be running, and the status of the application should be visible in the Grafana dashboard.

[Artillery](https://www.artillery.io/) hast to be installed to be able to run the load tests. Once it's installed, load tests can be executed.

The behavior of the test is defined in [this file](./load_testing/load-testing.yml). To run the tests, the following command must be executed from the folder containing the test definition file:
```bash
artillery run -t http://localhost:<APP_PORT> load-testing.yml -o <NAME_OF_THE_REPORT_FILE>.json
```
where `<APP_PORT>` is the port where the web application is running. And, `<NAME_OF_THE_REPORT_FILE>` is the name you want to give to the report that is automatically generated after the execution.

