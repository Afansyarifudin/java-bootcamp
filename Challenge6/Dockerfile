FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} binarfud.jar
ENTRYPOINT ["java", "-jar", "/binarfud.jar"]
