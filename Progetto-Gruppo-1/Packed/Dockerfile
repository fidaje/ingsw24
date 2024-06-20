FROM eclipse-temurin:17-jre-focal
#FROM ghcr.io/graalvm/graalvm-community:17

VOLUME /tmp
COPY target/Packed-0.1.jar packed.jar

EXPOSE 8085
ENTRYPOINT ["java", "-jar", "packed.jar"]

