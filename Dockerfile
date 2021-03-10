FROM gcr.io/distroless/java:11
COPY target/challange-accounts-0.0.1-SNAPSHOT.jar /app.jar
CMD [ "/app.jar" ]