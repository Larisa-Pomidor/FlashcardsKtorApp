FROM node:lts as nodejs

FROM gradle:7.4-jdk17
COPY --from=nodejs . .
COPY . .
RUN chmod +x /gradlew
RUN ./gradlew clean build

ENTRYPOINT ["./gradlew", "run"]
