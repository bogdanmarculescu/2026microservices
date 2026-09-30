# ---------- Build stage ----------
FROM maven:3.9.11-eclipse-temurin-21 AS build

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


# ---------- Run stage ----------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /usr/src/resolver

COPY --from=build /build/target/resolver-0.0.1-SNAPSHOT.jar ./resolver.jar

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "resolver.jar", "--spring.profiles.active=docker"]