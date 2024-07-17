FROM openjdk:17-jdk-alpine AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN chmod 777 ./mvnw
RUN ./mvnw clean package

FROM openjdk:17-jdk-alpine AS release
ARG JAR_FILE=/usr/app/target/*.jar
COPY --from=build $JAR_FILE server.jar
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/server.jar"]