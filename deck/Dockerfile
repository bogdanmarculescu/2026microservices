# ---------- Build stage ----------
FROM maven:3.9.11-eclipse-temurin-21 AS build

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


# ---------- Run stage ----------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /usr/src/deck

COPY --from=build /build/target/deck-0.0.1-SNAPSHOT.jar ./deck.jar

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "deck.jar", "--spring.profiles.active=docker"]