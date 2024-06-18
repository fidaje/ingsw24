FROM eclipse-temurin:17-jre-focal
#FROM ghcr.io/graalvm/graalvm-community:17

ADD target/Packed-0.1.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]

