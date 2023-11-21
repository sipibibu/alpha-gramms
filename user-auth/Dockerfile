FROM eclipse-temurin:17-jdk-alpine
LABEL authors="zhvl0"

WORKDIR app


COPY ./auth-jar.jar app.jar


ENTRYPOINT ["java","-jar", "app.jar"]