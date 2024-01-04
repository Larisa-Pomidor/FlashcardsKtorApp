FROM openjdk:8-jre-alpine

WORKDIR /app

COPY . /app

RUN chmod +x /app/gradlew
RUN ./gradlew wrapper --gradle-version=7.4
RUN ./gradlew clean build --debug

CMD ["./gradlew", "run"]