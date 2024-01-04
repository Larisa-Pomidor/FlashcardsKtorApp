FROM openjdk:8-jre-alpine

WORKDIR /app

COPY . /app

RUN chmod +x /app/gradlew

CMD ["./gradlew", "run"]