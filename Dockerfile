FROM eclipse-temurin:11.0.12_7-jdk
#RUN mvn clean package
ARG JAR_FILE=target/*.jar
COPY ./${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]