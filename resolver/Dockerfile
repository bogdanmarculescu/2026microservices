FROM openjdk:21-ea
COPY ./target/resolver-0.0.1-SNAPSHOT.jar /usr/src/resolver/
WORKDIR /usr/src/resolver/
EXPOSE 8006
CMD ["java", "-jar", "resolver-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]