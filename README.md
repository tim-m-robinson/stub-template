# Fuse Spring-Boot Arqullian QuickStart

This example demonstrates how you can use RedHat Fuse with Spring Boot and Arquillian.

# Building

The example can be built with

    mvn clean install spring-boot:repackage docker:build

## Running the example locally

Run container:
```
docker run -d --name stub -p 8443 stub-template:1.0-SNAPSHOT
```
## Query service 
Since we use a self-signed certificate for this quick start we must use the curl '-k' flag

Query running docker container:
```
curl -k https://127.0.0.1:8443/dummy/time
```

Expected response:
```
2018-08-12T19:07:37.644Z

```
## Running test
There are two types of tests configured in the pom file, integration tests started with `test` profile and unit tests started with `unit-test` profile
### Unit test
Unit tests run tests in the JVM without Docker. The unit tests generate JaCoCo code coverage reports. They can be found in the target/site directory after a successful test run

To run the unit tests start: 
```
mvn test -P unit-test
```
### Integration tests
Integration tests build a Docker image using the Spring-Boot runnable jar. The tests are then executed from 'outside'
and call the REST services exposed by the Docker container.

The parameter BASE_HOST is used to tell Maven where your Docker engine is running.

To run the test start:
```
mvn -DBASE_HOST="127.0.0.1" test -P test
```
