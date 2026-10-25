# Use official Java 21 image
FROM eclipse-temurin:21-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy Maven wrapper or pom.xml and source code
COPY . .

# Build the application using Maven wrapper or standard mvn
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Expose port 8080
EXPOSE 8080

# Run the jar file
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]