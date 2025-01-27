FROM maven:3.8.5-openjdk-17 AS build
COPY . /
RUN mvn -f /pom.xml clean package

FROM openjdk:17-jdk-slim
COPY --from=build target/*.jar application.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "application.jar"]