FROM maven:3.8.6-openjdk-8-slim
WORKDIR /app

# Copy the pom.xml file
COPY pom.xml .

# Copy the project source
COPY ./src ./src
COPY ./pom.xml ./pom.xml

RUN mvn clean install package #-DskipTests
#RUN ls -al
ENTRYPOINT ["java","-jar","target/demo-0.0.1-SNAPSHOT.jar"]

# makes port 8080 available outside the Docker container
EXPOSE 8080