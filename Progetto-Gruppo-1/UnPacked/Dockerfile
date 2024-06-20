FROM eclipse-temurin:17-jre-focal
#FROM ghcr.io/graalvm/graalvm-community:17

ENV MYSQL_ADDRESS=172.31.6.1
ENV MYSQL_PORT=3306



ADD target/UnPacked-0.1.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]

