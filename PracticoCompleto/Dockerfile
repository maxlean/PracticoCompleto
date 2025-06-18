FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/PracticoParte3-0.0.1.jar
COPY ${JAR_FILE} PracticoParte3-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/PracticoParte3-0.0.1.jar"]