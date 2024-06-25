FROM eclipse-temurin:17-jre-focal
#FROM ghcr.io/graalvm/graalvm-community:17

VOLUME /tmp
COPY target/Pantry-0.1.jar pantry.jar

EXPOSE 8084
ENTRYPOINT ["java", "-jar", "pantry.jar"]

