FROM openjdk:17
VOLUME /tmp
COPY target/receipt-processor-challenge-0.0.1-SNAPSHOT.jar receipt-processor-challenge-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/receipt-processor-challenge-0.0.1-SNAPSHOT.jar"]