FROM maven:3.8.5-openjdk-17 as build
COPY . /app
WORKDIR /app
RUN mvn package -DskipTests

FROM openjdk:17-jdk-alpine3.14 as deploy
MAINTAINER github.com/diegorezm

ARG version=1.0.0
ARG appname=convenience-store

COPY --from=build /app/target/${appname}-${version}.jar /app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]
