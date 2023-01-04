# mvn clean package -DskipTests
# docker build --tag=cambio-service:lastest .

FROM openjdk:17-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
ADD cambio-service/target/cambio-service-0.0.1-SNAPSHOT.jar cambio-service.jar
ENTRYPOINT ["java","-jar","/cambio-service.jar"]

