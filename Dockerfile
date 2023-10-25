FROM eclipse-temurin:17-jdk-alpine
LABEL authors="zhvl0"

WORKDIR app

COPY /main/build/libs/main-0.0.1-SNAPSHOT.jar app.jar


ENTRYPOINT ["java","-jar", "app.jar"]