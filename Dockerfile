FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/leave-service.jar app.jar
ENV APP_ENV=local
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
