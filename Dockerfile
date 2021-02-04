FROM openjdk:11.0-jdk
EXPOSE 8080
ARG JAR_FILE=target/challange-accounts-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]