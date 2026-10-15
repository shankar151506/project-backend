# ---- Build Stage ----
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests -B

# ---- Run Stage ----
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Create non-root user for security and prepare data directory
RUN addgroup -S appgroup && adduser -S appuser -G appgroup && \
    mkdir -p /app/data && chown -R appuser:appgroup /app

COPY --from=build /app/target/*.jar app.jar
RUN chown appuser:appgroup app.jar

# Switch to non-root user
USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
