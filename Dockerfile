FROM gradle:jdk8 as builder
WORKDIR /app
COPY src ./src
COPY build.gradle.kts ./build.gradle.kts
RUN --mount=type=cache,target=./.gradle gradle clean test install

FROM openjdk:8 as backend
WORKDIR /root
COPY --from=builder /app/build/libs ./
CMD ["java", "-jar", "/root/backend.jar"]