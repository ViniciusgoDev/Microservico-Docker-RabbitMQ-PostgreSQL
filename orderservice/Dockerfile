
FROM openjdk:21

WORKDIR /app

COPY ./target/orderService-0.0.1-SNAPSHOT.jar ./

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "orderService-0.0.1-SNAPSHOT.jar"]
