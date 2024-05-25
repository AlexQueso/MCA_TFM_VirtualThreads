# How to set up the environment for testing? 🛠

During this sections, a explanation on how to replicate the testing scenarios used during the project is provided. There are several pieces of software running at the same time, as shown in the following deployment diagram:

![](../results/assets/diagrams/deployment-diagram-load-testing.png)

The deployment of the testing environment can be divided into individual categories. 

* Deployment of the web application along with the database.
* Deployment of the Grafana and prometheus instances for the monitoring and visualization of the web application behavior.
* Execution of Artillery to run the load testing.

## How to deploy the web application and the database?

The more isolated the environment where the web application is running, the better. In order to achieve an isolated environment, the web application will run inside a Docker container. 

In order to achieve valid results, where the environment didn't change between executions, the Docker Engine was limited to 2 CPU cores and 6 GB of RAM usage. To do so, at least in a Windows machine, a `.wslconfig` file has to be added to the `$HOME` directory with the following content:
```
# Settings apply across all Linux distros running on WSL 2
[wsl2]

# Limits VM memory to use no more than 4 GB, this can be set as whole numbers using GB or MB
memory=6GB 

# Sets the VM to use two virtual processors
processors=2
```

The web application's JVM has to be limited also. That's achieve by telling Java the wanted limitations. In this specific case, where we have a dockerized application, the following property was added to the container environment, like it is done [here](./app_db/docker-compose.yaml#L34):
```
JAVA_TOOL_OPTIONS: '-Xms500M -Xmx500M'
```


### How to deploy the web application?

In order to be able 
