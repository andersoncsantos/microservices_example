FROM openjdk:17-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
ADD naming-server/target/naming-server-0.0.1-SNAPSHOT.jar naming-server.jar
ENTRYPOINT ["java","-jar","/naming-server.jar"]