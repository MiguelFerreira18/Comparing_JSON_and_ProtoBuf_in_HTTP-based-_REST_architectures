ARG version=1.0.0
ARG appname=convenience-store

COPY --from=build /app/target/${appname}-${version}.jar /app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]


FROM openjdk:17

COPY target/convenience-store-1.0.0.jar convenience-store-1.0.0.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","convenience-store-1.0.0.jar"]