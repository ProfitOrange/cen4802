# syntax=docker/dockerfile:1

FROM openjdk:11
WORKDIR /
ADD SoftwareIntegration/target/SoftwareIntegration-1.0.jar SoftwareIntegration-1.0.jar
EXPOSE 3000
CMD ["java", "-jar", "SoftwareIntegration-1.0.jar"]