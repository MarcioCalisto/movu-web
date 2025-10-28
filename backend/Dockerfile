# Stage 1: Build
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Copia arquivos do Maven
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Copia todo o código
COPY src src

# Build do JAR
RUN ./mvnw -DskipTests package

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENV JAVA_OPTS=""

CMD ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
