**Note**
This service should be started after <b>library-api-service-registry</b> service in order to make service discovery mechanism work properly!


To run <i>library-service</i> follow this steps:
1. Open a terminal and run ```mvn clean package -Dskiptests```
2. Run ```java -jar api-gateway-0.0.1-SNAPSHOT.jar```
