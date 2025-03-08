
FROM openjdk:21

WORKDIR /app


COPY target/trackingService-0.0.1-SNAPSHOT.jar .


EXPOSE 8083

ENTRYPOINT ["java", "-jar", "trackingService-0.0.1-SNAPSHOT.jar"]