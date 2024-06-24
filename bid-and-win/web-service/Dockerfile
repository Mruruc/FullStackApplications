FROM amazoncorretto:21

# Set the working directory
WORKDIR /app

# Copy the jar file
COPY target/*.jar /app/app.jar

# Expose the port
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
