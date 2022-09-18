FROM maven:3.8.6-amazoncorretto-17 as build

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN mvn package -Dmaven.test.skip

FROM openjdk:17
COPY --from=build target/KBE-Product-0.0.1-SNAPSHOT.jar KBE-Product-0.0.1-SNAPSHOT.jar
cmd java -Dserver.port=$PORT $JAVA_OPTS -jar KBE-Product-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java", "-jar", "KBE-Product-0.0.1-SNAPSHOT.jar"]