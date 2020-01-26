FROM openjdk:8-jre

COPY ./target/*.jar app.jar

RUN sh -c 'touch /app.jar'

EXPOSE 8080

CMD ["java", "-jar", "/app.jar"]
