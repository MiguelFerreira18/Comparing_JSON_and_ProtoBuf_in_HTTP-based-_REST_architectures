FROM openjdk:17

RUN ls -la /
RUN ls -la .

COPY target/convenience-store-1.0.0.jar convenience-store-1.0.0.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","convenience-store-1.0.0.jar"]