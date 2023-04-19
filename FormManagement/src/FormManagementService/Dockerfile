FROM openjdk:17-jdk-alpine
ARG JAR_FILE=targer/*.jar
COPY ./target/QuizGpt.FormManagement-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar", "com.quizGpt.formManagement.Main"]