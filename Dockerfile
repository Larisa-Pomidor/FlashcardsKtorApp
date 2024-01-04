FROM openjdk:8

WORKDIR /app

COPY . /app

RUN chmod +x /app/gradlew

RUN ./gradlew build

CMD ["java", "-jar", "/app/build/libs/untitled-jvm-1.0-SNAPSHOT.jar"]