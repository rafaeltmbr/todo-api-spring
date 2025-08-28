# Build stage
FROM ubuntu:latest AS build

WORKDIR /app
COPY . .

RUN apt update
RUN apt install openjdk-21-jdk -y
RUN apt install maven -y

RUN mvn clean install

# Prod image
FROM openjdk:21-jdk-alpine

WORKDIR /app
EXPOSE 8080

COPY --from=build /app/target/todolist-1.0.0.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
