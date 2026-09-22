FROM openjdk:21-ea
COPY ./target/deck-0.0.1-SNAPSHOT.jar /usr/src/deck/
WORKDIR /usr/src/deck/
EXPOSE 8001
CMD ["java", "-jar", "deck-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]