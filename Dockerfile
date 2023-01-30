FROM adoptopenjdk:11-jre-hotspot
#RUN mvn clean package
ARG JAR_FILE=target/*.jar
COPY ./${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]