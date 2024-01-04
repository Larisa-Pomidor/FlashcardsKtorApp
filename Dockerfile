FROM openjdk:8

WORKDIR /usr/src/app

COPY gradlew /app/gradlew

RUN chmod +x /app/gradlew

RUN /app/gradlew build

CMD ["java", "-jar", "build/libs/untitled-jvm-1.0-SNAPSHOT.jar"]