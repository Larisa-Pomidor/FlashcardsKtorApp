FROM openjdk:8

WORKDIR /usr/src/app

COPY . .

RUN ./gradlew build

CMD ["java", "-jar", "build/libs/untitled-jvm-1.0-SNAPSHOT.jar"]