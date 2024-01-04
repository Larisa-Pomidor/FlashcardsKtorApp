FROM openjdk:8

WORKDIR /app

COPY . /app

RUN chmod +x /app/gradlew

CMD ["./gradlew", "run"]