FROM maven:3.6.3-openjdk-16-slim

ARG JAR_FILE=target/*.jar

WORKDIR /opt/app
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]
