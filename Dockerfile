FROM openjdk:17-alpine

ENV database_url=jdbc:postgresql://localhost:5432/hotel
ENV username=postgres
ENV password=root

WORKDIR /app

COPY build/libs/demo-0.0.1-SNAPSHOT.jar /app/application.jar

EXPOSE 8080

CMD ["java", "-jar", "application.jar"]