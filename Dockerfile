FROM openjdk:8

WORKDIR /app

COPY . /app

RUN chmod +x /app/gradlew

RUN ./gradlew clean build

CMD ["./gradlew", "run"]