# Use an official OpenJDK runtime as a base image with JDK 17
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app
# copying the working directory inside the container
COPY . .
#for maven clean install
RUN chmod +x mvnw && ./mvnw clean install

# Specify the command to run on container startup
CMD ["java", "-jar", "target/client-0.0.1-SNAPSHOT.jar"]

