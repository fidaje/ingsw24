FROM eclipse-temurin:17-jre-focal
#FROM ghcr.io/graalvm/graalvm-community:17

VOLUME /tmp
COPY target/User-0.1.jar user.jar

EXPOSE 8089
ENTRYPOINT ["java", "-jar", "user.jar"]

