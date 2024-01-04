FROM adoptopenjdk:8-jdk-hotspot-bionic

WORKDIR /app

COPY . /app

RUN chmod +x /app/gradlew
RUN ./gradlew clean build --debug

CMD ["./gradlew", "run"]