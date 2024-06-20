FROM eclipse-temurin:17-jre-focal
#FROM ghcr.io/graalvm/graalvm-community:17

VOLUME /tmp
COPY target/Gateway-0.1.jar gateway.jar


EXPOSE 8080
ENTRYPOINT ["java", "-jar", "gateway.jar"]

