
FROM openjdk:21

WORKDIR /app

COPY ./target/notificationService-0.0.1-SNAPSHOT.jar .

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "notificationService-0.0.1-SNAPSHOT.jar"]