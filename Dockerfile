# ===== Build stage
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

# ===== Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /opt/app
ENV JAVA_OPTS=""
ENV SPRING_PROFILES_ACTIVE=prod
COPY --from=build /app/target/*jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
