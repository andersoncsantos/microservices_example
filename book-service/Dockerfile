# mvn clean package -DskipTests
# docker build --tag=cambio-service:lastest .

FROM openjdk:17-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
ADD book-service/target/book-service-0.0.1-SNAPSHOT.jar book-service.jar
ENTRYPOINT ["java","-jar","/book-service.jar"]

