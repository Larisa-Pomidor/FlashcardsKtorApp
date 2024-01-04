FROM openjdk:8

WORKDIR /app
RUN /app/gradlew build
RUN chmod +x /app/gradlew



CMD ["java", "-jar", "build/libs/untitled-jvm-1.0-SNAPSHOT.jar"]