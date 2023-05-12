# Read Me First

The following technologies stack used as part of building this project:

* Java 17
* Spring-Boot 3

# Getting Started

### How to run
- you can run application using docker or spring-boot maven both
#### Using Docker
* go to main directory 
```
cd /receipt-processor-challenge
```
* build spring boot image in docker using below command
```
docker build -t receipt-processor-challenge .
```
* run docker file 
```
docker run -p 8080:8080 receipt-processor-challenge
```
#### Using maven
- for this you need Java 17 installed on your machine to install java 17 follow given official docs
  https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
* go to main directory
```
cd /receipt-processor-challenge
```
* run below mvnw command
```
./mvnw spring-boot:run
```

### Guides

- Process Receipt
