FROM gradle:8.10.1-jdk21-alpine AS builder
WORKDIR /app
COPY settings.gradle.kts build.gradle.kts ./
RUN gradle build -x test --parallel > /dev/null 2>&1 || true

COPY src src
RUN gradle build -x test --parallel

FROM alpine/java:21
RUN mkdir /opt/app
COPY --from=builder /app/build/libs/*.jar /opt/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar", "--spring.profiles.active=local"]
