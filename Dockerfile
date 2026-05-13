FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY pet-service.jar app.jar
EXPOSE 3001
ENTRYPOINT ["java", "-jar", "app.jar"]