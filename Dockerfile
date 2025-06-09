# Build instructions:
# For local development: docker build -t ecomovil-api .
# For production (AWS/ECS): docker buildx build --platform linux/amd64 -t ecomovil-api:amd64 .

# Etapa 1: Build con Maven
FROM eclipse-temurin:21-jdk as builder

WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

COPY src src
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagen liviana solo con el .jar
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copiar solo el .jar de la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

# Default to production profile, can be overridden
ENV SPRING_PROFILES_ACTIVE=prod

# Optimizaciones de JVM para startup más rápido
CMD ["java", \
    "-Xms512m", \
    "-Xmx1024m", \
    "-XX:+UseG1GC", \
    "-XX:+UseStringDeduplication", \
    "-XX:+OptimizeStringConcat", \
    "-Dspring.jmx.enabled=false", \
    "-Dspring.main.lazy-initialization=false", \
    "-jar", "app.jar"]