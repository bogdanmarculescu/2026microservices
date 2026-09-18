FROM openjdk:21-ea
COPY ./target/round-0.0.1-SNAPSHOT.jar /usr/src/round/
WORKDIR /usr/src/round/
EXPOSE 8000
CMD ["java", "-jar", "round-0.0.1-SNAPSHOT.jar"]