[![Develop Build](https://travis-ci.com/MarcoFierimonte/product-service.svg?branch=master)](https://travis-ci.com/github/MarcoFierimonte/product-service)

# Product Service

The project simulate a shopping basket application. Products have different kind of sales taxes applied:
 - basic sale taxaction: 10% to all products excepts books, food and medicals
 - import duty taxaction: 5% to all imported products

## Building
The project is built with Maven, and a wrapper in the root is provided.
Run Gradle to build the project and to run the tests using the following command on Unix/macOS:

```bash
./mvnw <tasks-and-options>
```

or the following command on Windows:

```dos
mvnw <task-and-options>
```

### Execute test

```bash
./mvnw test
```