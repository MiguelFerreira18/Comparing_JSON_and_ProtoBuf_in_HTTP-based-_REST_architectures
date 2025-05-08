FROM openjdk:17


COPY target/Gateway-0.0.1-SNAPSHOT.jar Gateway-0.0.1-SNAPSHOT.jar

ENV IS_IN_KUBERNETES=true

EXPOSE 8082

ENTRYPOINT ["java","-jar","Gateway-0.0.1-SNAPSHOT.jar"]