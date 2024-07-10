# Use Amazon Corretto 21 with Alpine as base image
FROM amazoncorretto:17-alpine-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy necessary files for Gradle build
COPY . .

# Download and cache Gradle dependencies
RUN ./gradlew --version

# Copy remaining project files
COPY docker .

# Build the project with Gradle, avoiding tests
RUN ./gradlew clean build -x test

# Expose port 9000 (adjust as per your application's needs)
EXPOSE 8080

# Command to run the jar file
CMD ["java", "-jar", "./build/libs/drone.jar"]
