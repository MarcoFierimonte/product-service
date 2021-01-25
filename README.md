[![Develop Build](https://travis-ci.com/MarcoFierimonte/product-service.svg?branch=master)](https://travis-ci.com/github/MarcoFierimonte/product-service)
[![CodeFactor](https://www.codefactor.io/repository/github/marcofierimonte/product-service/badge)](https://www.codefactor.io/repository/github/marcofierimonte/product-service)

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

### Execute application
```bash
./mvnw exec:java -Dexec.mainClass=com.lastminute.store.product.boot.Main -Dexec.args="dataFolder=<path-to-datafiles=<comma-separed-files"
```
**Example:**
```bash
./mvnw exec:java -Dexec.mainClass=com.lastminute.store.product.boot.Main -Dexec.args="dataFolder=data files=Input1.csv,Input2.csv,Input3.csv"
```
### Expected Output

```
INPUT 1
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85

OUTPUT 1
1 book: 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 29.83

INPUT 2
1 imported box of chocolates at 10.00
1 imported bottle of perfume at 47.50

OUTPUT 2
1 imported box of chocolates: 10.50
1 imported bottle of perfume: 54.65
Sales Taxes: 7.65
Total: 65.15

INPUT 3
1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99
1 packet of headache pills at 9.75
1 box of imported chocolates at 11.25

OUTPUT 3
1 imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 9.75
1 box of imported chocolates: 11.85
Sales Taxes: 6.70
Total: 74.68
```

