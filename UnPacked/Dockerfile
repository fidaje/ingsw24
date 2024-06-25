FROM eclipse-temurin:17-jre-focal
#FROM ghcr.io/graalvm/graalvm-community:17

VOLUME /tmp
COPY target/UnPacked-0.1.jar unpacked.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "unpacked.jar"]

