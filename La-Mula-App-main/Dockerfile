# Use Amazon Corretto 21 as the base image
FROM amazoncorretto:21

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY build/libs/LaMulaApp-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# Define the command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]