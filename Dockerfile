FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY . /app

RUN chmod +x /app/gradlew
RUN ./gradlew clean build --debug

CMD ["./gradlew", "run"]