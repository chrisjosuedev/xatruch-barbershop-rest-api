## Build the application
FROM maven:3.9.5-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

## Run the application
FROM openjdk:21-oracle
WORKDIR /app
COPY --from=build /app/target/xatruch-barbershop-api-0.0.1-SNAPSHOT.jar ./xatruch-barbershop-api.jar
EXPOSE 9090

CMD [ "java", "-jar", "xatruch-barbershop-api.jar" ]