FROM maven:3.6.3-jdk-11 AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/review-service-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "review-service-0.0.1-SNAPSHOT.jar"]