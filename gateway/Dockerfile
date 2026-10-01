#Build step

FROM maven:3.9.11-eclipse-temurin-21 AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run step
FROM eclipse-temurin:21-jre-alpine
WORKDIR /usr/src/gateway
COPY --from=build /build/target/gateway-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8100
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]